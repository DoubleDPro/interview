package ru.usachev.interview.services.tasks.tests.repositories;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Set;
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
import ru.usachev.interview.services.tasks.tests.entites.Answer;
import ru.usachev.interview.services.tasks.tests.entites.TestTask;

@SpringBootTest(
    properties = {
        "spring.flyway.baseline-on-migrate=true",
        "spring.flyway.default-schema=flyway",
        "spring.flyway.schemas=tests,quizzes,text_tasks"
    }
)
@ContextConfiguration(initializers = {TestTaskRepositoryTest.Initializer.class})
@Testcontainers
class TestTaskRepositoryTest {

  @Container
  public static PostgreSQLContainer postgreSQLContainer = new PostgreSQLContainer("postgres:11.1")
      .withDatabaseName("integration-tests-db")
      .withUsername("postgres")
      .withPassword("postgres");

  @Autowired
  private TestTaskRepository repository;

  @Autowired
  private JdbcTemplate jdbcTemplate;

  @Test
  void insert() {
    Answer answer = new Answer(
        "correct answer",
        true
    );
    TestTask test = new TestTask(
        "content of test",
        true,
        Set.of(answer)
    );
    answer.setTest(test);
    repository.save(test);
    TestTask actualTest = repository.findById(test.id()).get();
    Answer actualAnswer = actualTest.answers().stream().findFirst().get();
    assertAll(
        () -> assertEquals(test.id(), actualTest.id()),
        () -> assertEquals(test.content(), actualTest.content()),
        () -> assertEquals(test.isOnlyOneAnswer(), actualTest.isOnlyOneAnswer()),
        () -> assertEquals(answer.id(), actualAnswer.id()),
        () -> assertEquals(answer.content(), actualAnswer.content()),
        () -> assertEquals(answer.isCorrect(), actualAnswer.isCorrect())
    );
  }

  @AfterEach
  void clearDB(){
    jdbcTemplate.execute("TRUNCATE TABLE tests.tests CASCADE");
  }

  static class Initializer
      implements ApplicationContextInitializer<ConfigurableApplicationContext> {
    public void initialize(ConfigurableApplicationContext configurableApplicationContext) {
      String[] params = {
          "spring.datasource.url=jdbc:tc:postgresql:11.1://ignored:1111/" + postgreSQLContainer.getDatabaseName(),
          "spring.datasource.username=" + postgreSQLContainer.getUsername(),
          "spring.datasource.password=" + postgreSQLContainer.getPassword(),
          "spring.flyway.default-schema=tests"
      };
      TestPropertyValues.of(
          params
      ).applyTo(configurableApplicationContext.getEnvironment());
    }
  }
}