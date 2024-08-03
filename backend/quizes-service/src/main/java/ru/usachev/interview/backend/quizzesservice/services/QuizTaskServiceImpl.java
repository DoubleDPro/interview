package ru.usachev.interview.backend.quizzesservice.services;

import org.springframework.stereotype.Service;
import ru.usachev.interview.backend.quizzesservice.entities.QuizTask;
import ru.usachev.interview.backend.quizzesservice.repositories.QuizTaskRepository;

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
