package ru.usachev.interview.backend.texttasksservice.services;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import ru.usachev.interview.backend.texttasksservice.entities.TextTask;
import ru.usachev.interview.backend.texttasksservice.repositories.TextTaskRepository;

@SpringBootTest(properties = {
    "spring.datasource.url=jdbc:postgresql://localhost:5432/postgres"
})
class TextTaskServiceTest {

  @Autowired
  private TextTaskService service;

  @Autowired
  private TextTaskRepository repository;

  @Autowired
  private JdbcTemplate jdbcTemplate;

  @Test
  void create() {
    TextTask textTask = new TextTask(
        "content of text task"
    );
    TextTask actualTextTask = service.create(textTask);
    assertAll(
        () -> assertEquals(textTask.id(), actualTextTask.id()),
        () -> assertEquals(textTask.content(), actualTextTask.content())
    );
  }

  @AfterEach
  void clearDB() {
    jdbcTemplate.execute("TRUNCATE TABLE text_tasks.text_tasks CASCADE");
  }

}