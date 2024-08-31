package ru.usachev.interview.services.testsservice.services;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;
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
import ru.usachev.interview.services.testsservice.entites.Answer;
import ru.usachev.interview.services.testsservice.entites.TestTask;
import ru.usachev.interview.services.testsservice.repositories.TestTaskRepository;

@SpringBootTest
@ContextConfiguration(initializers = {TestTaskServiceTest.Initializer.class})
@Testcontainers
class TestTaskServiceTest {

  @Container
  public static PostgreSQLContainer postgreSQLContainer = new PostgreSQLContainer("postgres:11.1")
      .withDatabaseName("integration-tests-db")
      .withUsername("postgres")
      .withPassword("postgres");

  @Autowired
  private TestTaskService service;

  @Autowired
  private TestTaskRepository repository;

  @Autowired
  private JdbcTemplate jdbcTemplate;

  @Test
  void create() {
    Answer answer = new Answer(
        "correct answer",
        true
    );
    TestTask test = new TestTask(
        "content of test",
        true,
        Set.of(answer)
    );
    TestTask actualTest = service.create(test);
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

  @Test
  void edit() {
    Answer answer = new Answer(
        "correct answer",
        true
    );
    TestTask test = new TestTask(
        "content of test",
        true,
        Set.of(answer)
    );
    TestTask expectedTest = service.create(test);
    Answer expectedAnswer = expectedTest.answers().stream().findFirst().get();
    expectedAnswer.setContent("new correct answer");
    expectedAnswer.setCorrect(false);
    expectedTest.setContent("new correct content");
    expectedTest.setOnlyOneAnswer(false);
    expectedTest.setAnswers(Set.of(expectedAnswer));
    TestTask actualTest = service.edit(expectedTest);
    Answer actualAnswer = actualTest.answers().stream().findFirst().get();
    assertAll(
        () -> assertEquals(expectedTest.id(), actualTest.id()),
        () -> assertEquals(expectedTest.content(), actualTest.content()),
        () -> assertEquals(expectedTest.isOnlyOneAnswer(), actualTest.isOnlyOneAnswer()),
        () -> assertEquals(expectedAnswer.id(), actualAnswer.id()),
        () -> assertEquals(expectedAnswer.content(), actualAnswer.content()),
        () -> assertEquals(expectedAnswer.isCorrect(), actualAnswer.isCorrect())
    );
  }

  @Test
  void delete() {
    Answer answer = new Answer(
        "correct answer",
        true
    );
    TestTask test = new TestTask(
        "content of test",
        true,
        Set.of(answer)
    );
    TestTask expectedTest = service.create(test);
    service.delete(expectedTest.id());
    Optional<TestTask> testTaskOptional = repository.findById(expectedTest.id());
    assertTrue(testTaskOptional.isEmpty());
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