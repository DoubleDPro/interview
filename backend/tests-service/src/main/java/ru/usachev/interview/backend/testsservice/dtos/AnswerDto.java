package ru.usachev.interview.backend.testsservice.dtos;

import java.util.UUID;

public record AnswerDto(
    UUID id,
    String content,
    boolean isCorrect
) {

}
