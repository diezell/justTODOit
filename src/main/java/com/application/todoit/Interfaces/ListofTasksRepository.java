package com.application.todoit.Interfaces;

import com.application.todoit.Models.*;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;

@Repository
@EntityScan(basePackages = {"com.application.todoit.Models.ListofTasks"})
public interface ListofTasksRepository extends JpaRepository<ListofTasks, UUID> {

}
