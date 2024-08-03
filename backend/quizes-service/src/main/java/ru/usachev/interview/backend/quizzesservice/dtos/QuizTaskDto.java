package ru.usachev.interview.backend.quizzesservice.dtos;

import java.util.UUID;

public record QuizTaskDto(
    UUID id,
    String content,
    String answer
) {

}
