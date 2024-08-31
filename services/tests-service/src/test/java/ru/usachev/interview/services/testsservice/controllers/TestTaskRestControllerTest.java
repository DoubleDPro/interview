package ru.usachev.interview.services.testsservice.controllers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.usachev.interview.services.testsservice.entites.TestTask;
import ru.usachev.interview.services.testsservice.services.TestTaskService;

@WebMvcTest(TestTaskRestController.class)
class TestTaskRestControllerTest {

  @Autowired
  private MockMvc mvc;

  @MockBean
  private TestTaskService testTaskService;

  @Mock
  private TestTask mockedTest;

  @Test
  void create() throws Exception {
    ObjectMapper objectMapper = new ObjectMapper();

    Map<String, Object> answer = new HashMap<>();
    answer.put("content", "correct_answer");
    answer.put("isCorrect", true);

    Map<String, Object> test = new HashMap<>();
    test.put("content", "content of test");
    test.put("isOnlyOneAnswer", true);
    test.put("answers", Set.of(answer));

    when(testTaskService.create(any(TestTask.class))).thenReturn(mockedTest);

    mvc.perform(
        MockMvcRequestBuilders
            .post("/tests/create")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(test))
            .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk());
  }

  @Test
  void edit() throws Exception {
    ObjectMapper objectMapper = new ObjectMapper();

    Map<String, Object> answer = new HashMap<>();
    answer.put("content", "correct_answer");
    answer.put("isCorrect", true);

    Map<String, Object> test = new HashMap<>();
    test.put("content", "content of test");
    test.put("isOnlyOneAnswer", true);
    test.put("answers", Set.of(answer));

    when(testTaskService.edit(any(TestTask.class))).thenReturn(mockedTest);

    mvc.perform(
        MockMvcRequestBuilders
            .put("/tests/edit")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(test))
            .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk());
  }

  @Test
  void delete() throws Exception {
    UUID id = UUID.randomUUID();
    mvc.perform(
        MockMvcRequestBuilders
            .delete("/tests/delete/{id}", id)
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk());
  }

}