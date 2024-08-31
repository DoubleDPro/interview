package ru.usachev.interview.services.texttasksservice.controllers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.usachev.interview.services.texttasksservice.entities.TextTask;
import ru.usachev.interview.services.texttasksservice.services.TextTaskService;

@WebMvcTest(TextTaskController.class)
class TextTaskControllerTest {

  @Autowired
  private MockMvc mvc;

  @MockBean
  private TextTaskService textTaskService;

  @Mock
  private TextTask mockedTextTask;

  @Test
  void create() throws Exception {
    ObjectMapper objectMapper = new ObjectMapper();

    Map<String, Object> textTask = new HashMap<>();
    textTask.put("content", "content of text task");

    when(textTaskService.create(any(TextTask.class))).thenReturn(mockedTextTask);

    mvc.perform(
        MockMvcRequestBuilders
            .post("/texttasks/create")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(textTask))
            .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk());
  }
}