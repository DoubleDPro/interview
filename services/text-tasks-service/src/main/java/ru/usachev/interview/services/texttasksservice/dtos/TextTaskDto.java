package ru.usachev.interview.services.texttasksservice.dtos;

import java.util.UUID;

public record TextTaskDto(
    UUID id,
    String content
) {

}
