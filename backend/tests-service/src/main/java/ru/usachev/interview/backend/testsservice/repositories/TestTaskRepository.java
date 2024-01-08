package ru.usachev.interview.backend.testsservice.repositories;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.usachev.interview.backend.testsservice.entites.TestTask;

public interface TestTaskRepository extends JpaRepository<TestTask, UUID> {

}
