package ru.usachev.interview.backend.texttasksservice.dtos;

import java.util.UUID;

public record TextTaskDto(
    UUID id,
    String content
) {

}
