package com.binhcodev.todo_list.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.binhcodev.todo_list.entities.User;
import com.binhcodev.todo_list.repositories.UserRepository;

import lombok.NoArgsConstructor;

@Service
@NoArgsConstructor
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User createUser(User user) {
        User existUser = userRepository.findUserByEmail(user.getEmail());
        return user;
    }
}
