package ru.usachev.interview.services.texttasksservice.repositories;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.usachev.interview.services.texttasksservice.entities.TextTask;

public interface TextTaskRepository extends JpaRepository<TextTask, UUID> {

}
