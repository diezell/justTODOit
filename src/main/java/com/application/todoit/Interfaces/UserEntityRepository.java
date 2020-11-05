package com.application.todoit.Interfaces;

import com.application.todoit.Models.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserEntityRepository extends JpaRepository<UserEntity, UUID> {

    UserEntity findByLogin(String login);

}
