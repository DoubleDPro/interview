package ru.usachev.interview.backend.quizzesservice.repositories;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import ru.usachev.interview.backend.quizzesservice.entities.QuizTask;

@SpringBootTest
class QuizTaskRepositoryTest {

  @Autowired
  private QuizTaskRepository repository;

  @Autowired
  private JdbcTemplate jdbcTemplate;

  @Test
  void insert() {
    QuizTask quiz = new QuizTask(
        "content of quiz",
        "answer of quiz"
    );
    repository.save(quiz);
    QuizTask actualQuiz = repository.findById(quiz.id()).get();
    assertAll(
        () -> assertEquals(quiz.id(), actualQuiz.id()),
        () -> assertEquals(quiz.content(), actualQuiz.content()),
        () -> assertEquals(quiz.answer(), actualQuiz.answer())
    );
  }

  @AfterEach
  void clearDB() {
  jdbcTemplate.execute("TRUNCATE TABLE quizzes.quizzes CASCADE ");
  }

}