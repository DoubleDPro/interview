package ru.usachev.interview.services.tasks.tests.services;

import java.util.UUID;
import ru.usachev.interview.services.tasks.tests.entites.TestTask;

public interface TestTaskService {

  TestTask create(TestTask test);

  TestTask edit(TestTask test);

  void delete(UUID id);

}
