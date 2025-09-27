package ru.usachev.interview.services.tasks.tests.dtos;

import java.util.UUID;

public record AnswerDto(
    UUID id,
    String content,
    boolean isCorrect
) {

}
