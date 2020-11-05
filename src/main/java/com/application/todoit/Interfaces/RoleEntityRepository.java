package com.application.todoit.Interfaces;

import com.application.todoit.Models.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface RoleEntityRepository extends JpaRepository<RoleEntity, UUID> {

    RoleEntity findByName(String name);

}
