package com.application.todoit.Interfaces;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.application.todoit.Models.Todo;

/**
 * @author marshakov
 */

public interface TodoRepository extends JpaRepository < Todo, Long > {
    List < Todo > findByUserName(String user);
}