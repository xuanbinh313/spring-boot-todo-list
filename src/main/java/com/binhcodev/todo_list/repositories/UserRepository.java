package com.binhcodev.todo_list.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import com.binhcodev.todo_list.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}
