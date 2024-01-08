package ru.usachev.interview.backend.testsservice.controllers;

import java.util.UUID;
import java.util.stream.Collectors;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.usachev.interview.backend.testsservice.dtos.AnswerDto;
import ru.usachev.interview.backend.testsservice.dtos.TestTaskDto;
import ru.usachev.interview.backend.testsservice.entites.Answer;
import ru.usachev.interview.backend.testsservice.entites.TestTask;
import ru.usachev.interview.backend.testsservice.services.TestTaskService;

@RestController
@RequestMapping("tests")
public class TestTaskRestController {

  private final TestTaskService service;

  public TestTaskRestController(TestTaskService service) {
    this.service = service;
  }

  @PostMapping(
      value = "create",
      consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE
  )
  public ResponseEntity<TestTaskDto> create(@RequestBody TestTaskDto testTaskDto) {
    TestTask testToCreate = convertFromDto(testTaskDto);
    TestTask createdTest = service.create(testToCreate);
    return ResponseEntity.ok(convertToDto(createdTest));
  }

  @PutMapping(
      value = "edit",
      consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE
  )
  public ResponseEntity<TestTaskDto> edit(@RequestBody TestTaskDto testTaskDto) {
    TestTask testToEdit = convertFromDto(testTaskDto);
    TestTask editedTest = service.edit(testToEdit);
    return ResponseEntity.ok(convertToDto(editedTest));
  }

  @DeleteMapping(
      value = "delete/{id}"
  )
  public ResponseEntity<?> delete(@PathVariable UUID id) {
    service.delete(id);
    return ResponseEntity.ok().build();
  }

  private TestTask convertFromDto(TestTaskDto testTaskDto) {
    return new TestTask(
        testTaskDto.content(),
        testTaskDto.isOnlyOneAnswer(),
        testTaskDto.answers().stream().map(this::convertFromDto).collect(Collectors.toSet())
    );
  }

  private TestTaskDto convertToDto(TestTask testTask) {
    return new TestTaskDto(
        testTask.id(),
        testTask.content(),
        testTask.isOnlyOneAnswer(),
        testTask.answers().stream().map(this::convertToDto).collect(Collectors.toSet()));
  }

  private Answer convertFromDto(AnswerDto answerDto) {
    return new Answer(
        answerDto.content(),
        answerDto.isCorrect()
    );
  }

  private AnswerDto convertToDto(Answer answer) {
    return new AnswerDto(
        answer.id(),
        answer.content(),
        answer.isCorrect()
    );
  }

}
