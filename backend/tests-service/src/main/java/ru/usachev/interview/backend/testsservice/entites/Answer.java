package ru.usachev.interview.backend.testsservice.entites;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.util.UUID;

@Entity
@Table(schema = "tests", name = "answers")
public class Answer {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  @Column(name = "id")
  private UUID id;

  @Column(name = "content")
  private String content;

  @Column(name = "is_correct")
  private boolean isCorrect;

  @ManyToOne
  @JoinColumn(name = "test_id", referencedColumnName = "id")
  private TestTask test;

  public void setTest(TestTask test) {
    this.test = test;
  }

  public Answer(){}

  public Answer(
      String content,
      boolean isCorrect
  ) {
    this.content = content;
    this.isCorrect = isCorrect;
  }

  public UUID id() {
    return id;
  }

  public String content() {
    return content;
  }

  public boolean isCorrect() {
    return isCorrect;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public void setCorrect(boolean correct) {
    isCorrect = correct;
  }
}
