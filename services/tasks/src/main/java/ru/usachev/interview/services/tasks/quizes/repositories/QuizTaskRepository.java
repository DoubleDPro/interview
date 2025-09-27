package ru.usachev.interview.services.tasks.quizes.repositories;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.usachev.interview.services.tasks.quizes.entities.QuizTask;

public interface QuizTaskRepository extends JpaRepository<QuizTask, UUID> {

}
