package com.binhcodev.todo_list.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.binhcodev.todo_list.entities.Todo;
import com.binhcodev.todo_list.services.TodoService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/api/todos")
public class TodoController {
    @Autowired
    private final TodoService todoService;

    @GetMapping("/")
    public ResponseEntity<List<Todo>> getAll(@RequestParam(required = false) String term) {
        List<Todo> todos = todoService.getAll(term);
        return new ResponseEntity<>(todos, HttpStatus.OK);
    }

    
}
