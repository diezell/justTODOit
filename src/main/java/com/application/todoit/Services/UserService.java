package com.application.todoit.Services;

import com.application.todoit.Models.User;

import java.util.*;

public interface UserService {

    User register(User user);

    List<User> getAll();

    User findByUsername(String username);

    User findById(UUID id);

    void delete(UUID id);

}
