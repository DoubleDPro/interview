package ru.usachev.interview.services.texttasksservice.services;

import org.springframework.stereotype.Service;
import ru.usachev.interview.services.texttasksservice.entities.TextTask;
import ru.usachev.interview.services.texttasksservice.repositories.TextTaskRepository;

@Service
public class TextTaskServiceImpl implements TextTaskService {

  private final TextTaskRepository repository;

  public TextTaskServiceImpl(TextTaskRepository repository) {
    this.repository = repository;
  }

  @Override
  public TextTask create(TextTask textTask) {
    return repository.save(textTask);
  }

}
