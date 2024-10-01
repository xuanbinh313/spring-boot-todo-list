package com.binhcodev.todo_list.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.binhcodev.todo_list.entities.Todo;

public interface TodoRepository extends JpaRepository<Todo,Long> {
    
}
