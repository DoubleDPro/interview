package ru.usachev.interview.backend.testsservice.repositories;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Set;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import ru.usachev.interview.backend.testsservice.entites.Answer;
import ru.usachev.interview.backend.testsservice.entites.TestTask;

@SpringBootTest
class TestTaskRepositoryTest {

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
}