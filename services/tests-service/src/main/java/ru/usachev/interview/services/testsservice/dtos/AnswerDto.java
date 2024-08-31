package ru.usachev.interview.services.testsservice.dtos;

import java.util.UUID;

public record AnswerDto(
    UUID id,
    String content,
    boolean isCorrect
) {

}
