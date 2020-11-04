package com.application.todoit.Interfaces;

import com.application.todoit.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {

    User findByUsername(String name);

}
