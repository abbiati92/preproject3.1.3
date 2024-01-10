package com.example.web.service;

import com.example.web.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {
    List<User> getAllUsers();

    User getUserById(Integer id);

    void createUser(User user);

    void updateUser(User user);

    void deleteUserById(Integer id);

    User findByUsername(String username);

    List<User> findAllWithRoles();

}
