package ru.usachev.interview.services.tasks.tests.services;

import java.util.UUID;
import org.springframework.stereotype.Service;
import ru.usachev.interview.services.tasks.tests.entites.TestTask;
import ru.usachev.interview.services.tasks.tests.repositories.TestTaskRepository;

@Service
public class TestTaskServiceImpl implements TestTaskService {

  private final TestTaskRepository repository;

  public TestTaskServiceImpl(TestTaskRepository testRepository) {
    this.repository = testRepository;
  }

  @Override
  public TestTask create(TestTask test) {
    return repository.save(test);
  }

  @Override
  public TestTask edit(TestTask test) {
    return repository.save(test);
  }

  @Override
  public void delete(UUID id) {
    repository.deleteById(id);
  }
}
