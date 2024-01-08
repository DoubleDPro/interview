package ru.usachev.interview.backend.testsservice.entites;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(schema = "tests", name = "tests")
public class TestTask {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  @Column(name = "id")
  private UUID id;

  @Column(name = "content")
  private String content;

  @Column(name = "is_only_one_answer")
  private boolean isOnlyOneAnswer;

  @OneToMany(cascade = CascadeType.ALL, mappedBy = "test", fetch = FetchType.EAGER)
  private Set<Answer> answers;

  protected TestTask() {}

  public TestTask(
      String content,
      boolean isOnlyOneAnswer,
      Set<Answer> answers
  ) {
    this.content = content;
    this.isOnlyOneAnswer = isOnlyOneAnswer;
    this.answers = answers;
    this.answers.forEach(a -> a.setTest(this));
  }

  public UUID id() {
    return id;
  }

  public String content() {
    return content;
  }

  public boolean isOnlyOneAnswer() {
    return isOnlyOneAnswer;
  }

  public Set<Answer> answers() {
    return new HashSet<>(answers);
  }

  public void setContent(String content) {
    this.content = content;
  }

  public void setOnlyOneAnswer(boolean onlyOneAnswer) {
    isOnlyOneAnswer = onlyOneAnswer;
  }

  public void setAnswers(Set<Answer> answers) {
    this.answers = answers;
  }
}
