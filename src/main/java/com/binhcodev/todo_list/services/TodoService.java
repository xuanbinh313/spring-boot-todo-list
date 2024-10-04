package com.binhcodev.todo_list.services;

import java.util.List;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.binhcodev.todo_list.dtos.TodoDto;
import com.binhcodev.todo_list.entities.Todo;
import com.binhcodev.todo_list.entities.User;
import com.binhcodev.todo_list.repositories.TodoRepository;
import com.binhcodev.todo_list.repositories.UserRepository;

@Service
public class TodoService {
    @Autowired
    private TodoRepository todoRepository;

    @Autowired
    private UserRepository userRepository;

    public List<Todo> getAll(String email) {
        Optional<User> optionalUser = userRepository.findByEmail(email);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            return todoRepository.findAllByUser(user);
        } else {
            // Handle the case where the user is not found, e.g., return an empty list
            return List.of();
        }
    }

    public Todo create(String email, TodoDto todoDto) {
        Optional<User> optionalUser = userRepository.findByEmail(email);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            Todo todo = Todo
                    .builder()
                    .title(todoDto.getTitle())
                    .description(todoDto.getDescription())
                    .user(user)
                    .build();
            return todoRepository.save(todo);
        } else {
            // Handle the case where the user is not found, e.g., return null
            return null;
        }
    }

}
