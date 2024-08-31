package ru.usachev.interview.services.quizzesservice.repositories;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.usachev.interview.services.quizzesservice.entities.QuizTask;

public interface QuizTaskRepository extends JpaRepository<QuizTask, UUID> {

}
