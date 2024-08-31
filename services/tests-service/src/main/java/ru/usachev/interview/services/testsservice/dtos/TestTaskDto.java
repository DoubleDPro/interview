package ru.usachev.interview.services.testsservice.dtos;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

public record TestTaskDto(
    UUID id,
    String content,
    boolean isOnlyOneAnswer,
    Set<AnswerDto> answers
) {

  public TestTaskDto(
      UUID id,
      String content,
      boolean isOnlyOneAnswer,
      Set<AnswerDto> answers
  ) {
    this.id = id;
    this.content = content;
    this.isOnlyOneAnswer = isOnlyOneAnswer;
    this.answers = Optional.ofNullable(answers).map(HashSet::new).orElse(new HashSet<>());
  }
}
