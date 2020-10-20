package com.application.todoit.Interfaces;

import com.application.todoit.Models.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {

}
