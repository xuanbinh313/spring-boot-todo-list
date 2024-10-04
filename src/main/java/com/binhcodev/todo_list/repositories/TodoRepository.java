package com.binhcodev.todo_list.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.binhcodev.todo_list.entities.Todo;
import com.binhcodev.todo_list.entities.User;

import java.util.List;


public interface TodoRepository extends JpaRepository<Todo,Long> {
    List<Todo> findAllByUser(User user);
}
