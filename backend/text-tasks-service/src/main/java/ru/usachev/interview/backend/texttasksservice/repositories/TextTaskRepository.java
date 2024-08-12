package ru.usachev.interview.backend.texttasksservice.repositories;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.usachev.interview.backend.texttasksservice.entities.TextTask;

public interface TextTaskRepository extends JpaRepository<TextTask, UUID> {

}
