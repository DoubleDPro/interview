package ru.usachev.interview.services.texttasksservice.repositories;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

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
import ru.usachev.interview.services.texttasksservice.entities.TextTask;

@SpringBootTest
@ContextConfiguration(initializers = {TextTaskRepositoryTest.Initializer.class})
@Testcontainers
class TextTaskRepositoryTest {

  @Container
  public static PostgreSQLContainer postgreSQLContainer = new PostgreSQLContainer("postgres:11.1")
      .withDatabaseName("integration-tests-db")
      .withUsername("postgres")
      .withPassword("postgres");

  @Autowired
  private TextTaskRepository repository;

  @Autowired
  private JdbcTemplate jdbcTemplate;

  @Test
  void insert() {
    TextTask textTask = new TextTask(
        "content of text task"
    );
    repository.save(textTask);
    TextTask actualTextTask = repository.findById(textTask.id()).get();
    assertAll(
        () -> assertEquals(textTask.id(), actualTextTask.id()),
        () -> assertEquals(textTask.content(), actualTextTask.content())
    );
  }

  @AfterEach
  void clearDB() {
    jdbcTemplate.execute("TRUNCATE TABLE text_tasks.text_tasks CASCADE");
  }

  static class Initializer
      implements ApplicationContextInitializer<ConfigurableApplicationContext> {
    public void initialize(ConfigurableApplicationContext configurableApplicationContext) {
      String[] params = {
          "spring.datasource.url=jdbc:tc:postgresql:11.1://ignored:1111/" + postgreSQLContainer.getDatabaseName(),
          "spring.datasource.username=" + postgreSQLContainer.getUsername(),
          "spring.datasource.password=" + postgreSQLContainer.getPassword(),
          "spring.flyway.default-schema=text_tasks"
      };
      TestPropertyValues.of(
          params
      ).applyTo(configurableApplicationContext.getEnvironment());
    }
  }
}