package ru.usachev.interview.services.tasks.texttasks.controllers;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.usachev.interview.services.tasks.texttasks.dtos.TextTaskDto;
import ru.usachev.interview.services.tasks.texttasks.entities.TextTask;
import ru.usachev.interview.services.tasks.texttasks.services.TextTaskService;

@RestController
@RequestMapping("texttasks")
public class TextTaskController {

  private final TextTaskService service;


  public TextTaskController(TextTaskService service) {
    this.service = service;
  }

  @CrossOrigin
  @PostMapping(
      value = "create",
      consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE
  )
  public ResponseEntity<TextTaskDto> create(@RequestBody TextTaskDto textTaskDto) {
    TextTask textTaskToCreate = convertFromDto(textTaskDto);
    TextTask createdTextTask = service.create(textTaskToCreate);
    return ResponseEntity.ok(convertToDto(createdTextTask));
  }

  private TextTask convertFromDto(TextTaskDto textTaskDto) {
    return new TextTask(
        textTaskDto.content()
    );
  }

  private TextTaskDto convertToDto(TextTask textTask) {
    return new TextTaskDto(
        textTask.id(),
        textTask.content()
    );
  }
}
