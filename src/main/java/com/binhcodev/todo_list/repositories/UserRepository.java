package com.binhcodev.todo_list.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.binhcodev.todo_list.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {
    @Query("SELECT b FROM User b WHERE b.email = :email")
    public User findUserByEmail(@Param("email") String email);
}
