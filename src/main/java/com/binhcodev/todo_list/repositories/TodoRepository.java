package com.binhcodev.todo_list.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.binhcodev.todo_list.entities.Todo;

public interface TodoRepository extends JpaRepository<Todo, Long> {
    @Query("SELECT b FROM Todo b WHERE b.user.id = :userId AND (:term IS NULL OR b.title LIKE %:term% OR b.description LIKE %:term%)")
    Page<Todo> findAllByUser(@Param("userId") Long userId, @Param("term") String term, Pageable pageable);
}
