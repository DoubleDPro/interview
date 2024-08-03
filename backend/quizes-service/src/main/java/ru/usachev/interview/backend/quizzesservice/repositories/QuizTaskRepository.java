package ru.usachev.interview.backend.quizzesservice.repositories;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.usachev.interview.backend.quizzesservice.entities.QuizTask;

public interface QuizTaskRepository extends JpaRepository<QuizTask, UUID> {

}
