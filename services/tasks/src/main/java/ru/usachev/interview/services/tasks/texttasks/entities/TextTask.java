package ru.usachev.interview.services.tasks.texttasks.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.UUID;

@Entity
@Table(schema = "text_tasks", name = "text_tasks")
public class TextTask {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  @Column(name = "content")
  private String content;

  protected TextTask() {}

  public TextTask(
      String content
  ) {
    this.content = content;
  }

  public UUID id() {
    return id;
  }

  public String content() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

}
