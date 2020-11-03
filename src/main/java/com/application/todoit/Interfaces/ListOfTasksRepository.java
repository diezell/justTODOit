package com.application.todoit.Interfaces;

import com.application.todoit.Models.*;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import java.util.UUID;

/**
 * Репозиторий для списков
 */
@Repository
@EntityScan(basePackages = {"com.application.todoit.Models.ListOfTasks"})
public interface ListOfTasksRepository extends JpaRepository<ListOfTasks, UUID>, JpaSpecificationExecutor<ListOfTasks> {

}