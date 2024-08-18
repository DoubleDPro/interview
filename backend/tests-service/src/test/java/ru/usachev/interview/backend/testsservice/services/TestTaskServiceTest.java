package ru.usachev.interview.backend.testsservice.services;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;
import java.util.Set;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import ru.usachev.interview.backend.testsservice.entites.Answer;
import ru.usachev.interview.backend.testsservice.entites.TestTask;
import ru.usachev.interview.backend.testsservice.repositories.TestTaskRepository;

@SpringBootTest(properties = {
    "spring.datasource.url=jdbc:postgresql://localhost:5432/postgres"
})
class TestTaskServiceTest {

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
}