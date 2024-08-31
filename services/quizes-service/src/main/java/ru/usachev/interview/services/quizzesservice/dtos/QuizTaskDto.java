package ru.usachev.interview.services.quizzesservice.dtos;

import java.util.UUID;

public record QuizTaskDto(
    UUID id,
    String content,
    String answer
) {

}
