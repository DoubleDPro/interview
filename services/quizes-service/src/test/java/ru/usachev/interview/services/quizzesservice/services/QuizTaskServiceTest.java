package ru.usachev.interview.services.quizzesservice.services;

import static org.junit.jupiter.api.Assertions.*;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import ru.usachev.interview.services.quizzesservice.entities.QuizTask;
import ru.usachev.interview.services.quizzesservice.repositories.QuizTaskRepository;

@SpringBootTest
@ContextConfiguration(initializers = {QuizTaskServiceTest.Initializer.class})
@Testcontainers
class QuizTaskServiceTest {

  @Container
  public static PostgreSQLContainer postgreSQLContainer = new PostgreSQLContainer("postgres:11.1")
      .withDatabaseName("integration-tests-db")
      .withUsername("postgres")
      .withPassword("postgres");

  @Autowired
  private QuizTaskService service;

  @Autowired
  private QuizTaskRepository repository;

  @Autowired
  private JdbcTemplate jdbcTemplate;

  @Test
  @Transactional
  void create() {
    QuizTask quiz = new QuizTask(
        "content of quiz",
        "answer of quiz"
    );
    QuizTask actualQuiz = service.create(quiz);
    assertAll(
        () -> assertEquals(quiz.id(), actualQuiz.id()),
        () -> assertEquals(quiz.content(), actualQuiz.content()),
        () -> assertEquals(quiz.answer(), actualQuiz.answer())
    );
  }

  @AfterEach
  void clearDB(){
    jdbcTemplate.execute("TRUNCATE TABLE quizzes.quizzes CASCADE");
  }

  static class Initializer
      implements ApplicationContextInitializer<ConfigurableApplicationContext> {
    public void initialize(ConfigurableApplicationContext configurableApplicationContext) {
      String[] params = {
          "spring.datasource.url=jdbc:tc:postgresql:11.1://ignored:1111/" + postgreSQLContainer.getDatabaseName(),
          "spring.datasource.username=" + postgreSQLContainer.getUsername(),
          "spring.datasource.password=" + postgreSQLContainer.getPassword(),
          "spring.flyway.default-schema=quizzes"
      };
      TestPropertyValues.of(
          params
      ).applyTo(configurableApplicationContext.getEnvironment());
    }
  }

}