package com.example.web.dao;


import com.example.web.model.User;

import java.util.List;

public interface UserDao {
    List<User> getUsersList();
    User getUser(Long id);
    void addUser(User user);
    void deleteUser(Long id);
    void editUser(User user);
    User findByUsername(String username);
    void setUserRole(Long id, int roleId);
}
