package ru.usachev.interview.services.testsservice.services;

import java.util.UUID;
import ru.usachev.interview.services.testsservice.entites.TestTask;

public interface TestTaskService {

  TestTask create(TestTask test);

  TestTask edit(TestTask test);

  void delete(UUID id);

}
