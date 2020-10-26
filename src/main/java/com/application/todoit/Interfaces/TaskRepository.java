package com.application.todoit.Interfaces;

import com.application.todoit.Models.Task;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * java-doc
 */
@Repository
@EntityScan(basePackages = {"com.application.todoit.Models.Task"})
public interface TaskRepository extends JpaRepository<Task, UUID> {

}
