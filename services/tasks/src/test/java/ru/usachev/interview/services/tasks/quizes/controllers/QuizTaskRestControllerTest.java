package ru.usachev.interview.services.tasks.quizes.controllers;

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
import ru.usachev.interview.services.tasks.quizes.entities.QuizTask;
import ru.usachev.interview.services.tasks.quizes.services.QuizTaskService;

@WebMvcTest(QuizTaskRestController.class)
class QuizTaskRestControllerTest {

  @Autowired
  private MockMvc mvc;

  @MockBean
  private QuizTaskService quizTaskService;

  @Mock
  private QuizTask mockedQuiz;

  @Test
  void create() throws Exception {
    ObjectMapper objectMapper = new ObjectMapper();

    Map<String, Object> quiz = new HashMap<>();
    quiz.put("content", "content of quiz");
    quiz.put("answer", "answer of quiz");

    when(quizTaskService.create(any(QuizTask.class))).thenReturn(mockedQuiz);

    mvc.perform(
        MockMvcRequestBuilders
            .post("/quizzes/create")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(quiz))
            .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk());
  }
}