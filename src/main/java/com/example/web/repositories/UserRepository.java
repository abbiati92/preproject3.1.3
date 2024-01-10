package com.example.web.repositories;

import com.example.web.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User findByUsername(String username);

    @Query("SELECT DISTINCT u FROM User u LEFT JOIN FETCH u.userRoles")
    List<User> findAllWithRoles();
}
