package com.application.todoit.Interfaces;

import com.application.todoit.Models.Task;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * Репозиторий для заданий
 */
@Repository
@EntityScan(basePackages = {"com.application.todoit.Models.Task"})
public interface TaskRepository extends JpaRepository<Task, UUID>, JpaSpecificationExecutor<Task> {

}
