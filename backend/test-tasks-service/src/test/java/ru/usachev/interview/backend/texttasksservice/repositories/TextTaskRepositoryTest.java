package ru.usachev.interview.backend.texttasksservice.repositories;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import ru.usachev.interview.backend.texttasksservice.entities.TextTask;

@SpringBootTest
class TextTaskRepositoryTest {

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

  void clearDB() {
    jdbcTemplate.execute("TRUNCATE TABLE text_tasks.text_tasks CASCADE");
  }
}