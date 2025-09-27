package ru.usachev.interview.services.tasks.quizes.dtos;

import java.util.UUID;

public record QuizTaskDto(
    UUID id,
    String content,
    String answer
) {

}
