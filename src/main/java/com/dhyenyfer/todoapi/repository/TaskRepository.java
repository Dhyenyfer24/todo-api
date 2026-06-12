package com.dhyenyfer.todoapi.repository;

import com.dhyenyfer.todoapi.entity.Task;
import com.dhyenyfer.todoapi.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {

    List<Task> findByUser(User user);
}