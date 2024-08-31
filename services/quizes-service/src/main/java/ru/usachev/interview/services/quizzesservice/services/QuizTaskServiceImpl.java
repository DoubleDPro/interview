package ru.usachev.interview.services.quizzesservice.services;

import org.springframework.stereotype.Service;
import ru.usachev.interview.services.quizzesservice.entities.QuizTask;
import ru.usachev.interview.services.quizzesservice.repositories.QuizTaskRepository;

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
