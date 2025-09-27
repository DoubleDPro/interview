package ru.usachev.interview.services.tasks.texttasks.repositories;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.usachev.interview.services.tasks.texttasks.entities.TextTask;

public interface TextTaskRepository extends JpaRepository<TextTask, UUID> {

}
