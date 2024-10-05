package com.binhcodev.todo_list.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/todos")
public class TodoController {
    @Autowired
    private TodoService todoService;

    @GetMapping
    public ResponseEntity<Map<String, Object>> getAllTodosForCurrentUser(@RequestParam(required = false) String term,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int limit) {
        Page<Todo> todos = todoService.getAll(term, page, limit);

        // Build response
        Map<String, Object> response = new HashMap<>();
        response.put("data", todos.getContent());
        response.put("page", page);
        response.put("limit", limit);
        response.put("total", todos.getTotalElements());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Todo> getOneTodo(@PathVariable Long id) {
        return todoService.findOneTodo(id).map(x -> new ResponseEntity<>(x, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Todo> createTodoForCurrentUser(@RequestBody TodoDto todoDto) {
        Todo todo = todoService.create(todoDto);
        return new ResponseEntity<>(todo, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Todo> updateTodo(@PathVariable Long id, @RequestBody TodoDto todoDto) {
        return todoService.findOneTodo(id).map(x -> new ResponseEntity<>(x, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
