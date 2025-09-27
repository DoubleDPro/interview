package ru.usachev.interview.services.tasks.texttasks.dtos;

import java.util.UUID;

public record TextTaskDto(
    UUID id,
    String content
) {

}
