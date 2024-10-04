package com.binhcodev.todo_list.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.binhcodev.todo_list.dtos.TodoDto;
import com.binhcodev.todo_list.entities.Todo;
import com.binhcodev.todo_list.services.TodoService;
import com.binhcodev.todo_list.services.UserService;

@RestController
@RequestMapping("/todos")
public class TodoController {
    @Autowired
    private TodoService todoService;
    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<Todo>> getAllTodosForCurrentUser(@RequestParam(required = false) String term) {
        String email = userService.getCurrentUserEmail();
        if(email == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        List<Todo> todos = todoService.getAll(email);
        return new ResponseEntity<>(todos, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Todo> createTodoForCurrentUser(@RequestBody TodoDto todoDto) {
        String email = userService.getCurrentUserEmail();
        if(email == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        Todo todo = todoService.create(email, todoDto);
        return new ResponseEntity<>(todo, HttpStatus.CREATED);
    }

}
