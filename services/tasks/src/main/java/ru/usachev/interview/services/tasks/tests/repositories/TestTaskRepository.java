package ru.usachev.interview.services.tasks.tests.repositories;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.usachev.interview.services.tasks.tests.entites.TestTask;

public interface TestTaskRepository extends JpaRepository<TestTask, UUID> {

}
