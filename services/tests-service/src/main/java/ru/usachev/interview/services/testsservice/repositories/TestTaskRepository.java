package ru.usachev.interview.services.testsservice.repositories;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.usachev.interview.services.testsservice.entites.TestTask;

public interface TestTaskRepository extends JpaRepository<TestTask, UUID> {

}
