package com.binhcodev.todo_list.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
    @Autowired
    private UserService userService;

    public Page<Todo> getAll(String term, int page, int limit) {
        String currentEmail = userService.getCurrentUserEmail();
        Optional<User> optionalUser = userRepository.findByEmail(currentEmail);
        Pageable pageable = PageRequest.of(page - 1, limit);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            return todoRepository.findAllByUser(user.getId(), term, pageable);
        } else {
            // Handle the case where the user is not found, e.g., return an empty list
            return Page.empty();
        }
    }

    public Todo create(TodoDto todoDto) {
        String currentEmail = userService.getCurrentUserEmail();
        Optional<User> optionalUser = userRepository.findByEmail(currentEmail);
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

    public Optional<Todo> update(Long id, TodoDto todoDto) {
        return todoRepository.findById(id).map(x -> {
            x.setTitle(todoDto.getTitle());
            x.setDescription(todoDto.getDescription());
            return todoRepository.save(x);
        });

    }

    public Optional<Todo> findOneTodo(Long id) {
        return todoRepository.findById(id);
    }

}
