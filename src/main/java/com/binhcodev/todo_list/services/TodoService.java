package com.binhcodev.todo_list.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.binhcodev.todo_list.entities.Todo;
import com.binhcodev.todo_list.repositories.TodoRepository;

@Service
public class TodoService {
    @Autowired
    private final TodoRepository todoRepository;

    public List<Todo> getAll(String search) {
        return todoRepository.findAll();
    }

}
