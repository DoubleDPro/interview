package ru.usachev.interview.backend.testsservice.services;

import java.util.UUID;
import ru.usachev.interview.backend.testsservice.entites.TestTask;

public interface TestTaskService {

  TestTask create(TestTask test);

  TestTask edit(TestTask test);

  void delete(UUID id);

}
