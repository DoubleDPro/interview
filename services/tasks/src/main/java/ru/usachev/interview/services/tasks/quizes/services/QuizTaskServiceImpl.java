package ru.usachev.interview.services.tasks.quizes.services;

import org.springframework.stereotype.Service;
import ru.usachev.interview.services.tasks.quizes.entities.QuizTask;
import ru.usachev.interview.services.tasks.quizes.repositories.QuizTaskRepository;

@Service
public class QuizTaskServiceImpl implements QuizTaskService {

  private final QuizTaskRepository repository;

  public QuizTaskServiceImpl(QuizTaskRepository repository) {
    this.repository = repository;
  }

  @Override
  public QuizTask create(QuizTask quiz) {
    return repository.save(quiz);
  }
}
