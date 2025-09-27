package ru.usachev.interview.services.tasks.quizes.controllers;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.usachev.interview.services.tasks.quizes.dtos.QuizTaskDto;
import ru.usachev.interview.services.tasks.quizes.entities.QuizTask;
import ru.usachev.interview.services.tasks.quizes.services.QuizTaskService;

@RestController
@RequestMapping("quizzes")
public class QuizTaskRestController {

  private final QuizTaskService service;

  public QuizTaskRestController(QuizTaskService service) {
    this.service = service;
  }

  @CrossOrigin
  @PostMapping(
      value = "create",
      consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE
  )
  public ResponseEntity<QuizTaskDto> create(@RequestBody QuizTaskDto quizTaskDto) {
    QuizTask quizToCreate = convertFromDto(quizTaskDto);
    QuizTask createdQuiz = service.create(quizToCreate);
    return ResponseEntity.ok(convertToDto(createdQuiz));
  }

  private QuizTask convertFromDto(QuizTaskDto quizTaskDto) {
    return new QuizTask(
        quizTaskDto.content(),
        quizTaskDto.answer()
    );
  }

  private QuizTaskDto convertToDto(QuizTask quiz) {
    return new QuizTaskDto(
        quiz.id(),
        quiz.content(),
        quiz.answer()
    );
  }
}
