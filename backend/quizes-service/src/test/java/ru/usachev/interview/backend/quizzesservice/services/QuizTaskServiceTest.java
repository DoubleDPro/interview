package ru.usachev.interview.backend.quizzesservice.services;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import ru.usachev.interview.backend.quizzesservice.entities.QuizTask;
import ru.usachev.interview.backend.quizzesservice.repositories.QuizTaskRepository;

@SpringBootTest
class QuizTaskServiceTest {

  @Autowired
  private QuizTaskService service;

  @Autowired
  private QuizTaskRepository repository;

  @Autowired
  private JdbcTemplate jdbcTemplate;

  @Test
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

}