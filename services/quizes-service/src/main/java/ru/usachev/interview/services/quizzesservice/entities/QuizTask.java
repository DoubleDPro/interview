package ru.usachev.interview.services.quizzesservice.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.UUID;

@Entity
@Table(schema = "quizzes", name = "quizzes")
public class QuizTask {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  @Column(name = "content")
  private String content;

  @Column(name = "answer")
  private String answer;

  protected QuizTask() {}

  public QuizTask(
      String content,
      String answer
  ) {
    this.content = content;
    this.answer = answer;
  }

  public UUID id() {
    return id;
  }

  public String content() {
    return content;
  }

  public String answer() {
    return answer;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public void setAnswer(String answer) {
    this.answer = answer;
  }
}
