package com.dhyenyfer.todoapi.service;

import com.dhyenyfer.todoapi.dto.TaskResponse;
import com.dhyenyfer.todoapi.dto.request.TaskRequest;
import com.dhyenyfer.todoapi.dto.request.UpdateTaskRequest;
import com.dhyenyfer.todoapi.entity.Task;
import com.dhyenyfer.todoapi.entity.User;
import com.dhyenyfer.todoapi.enums.TaskStatus;
import com.dhyenyfer.todoapi.exception.ResourceNotFoundException;
import com.dhyenyfer.todoapi.repository.TaskRepository;
import com.dhyenyfer.todoapi.security.CustomUserDetails;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }


    private User getLoggedUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails userDetails = (CustomUserDetails) auth.getPrincipal();
        return userDetails.getUser();
    }

    public TaskResponse create(TaskRequest dto) {
        User user = getLoggedUser();

        Task task = new Task();
        task.setTitle(dto.getTitle());
        task.setDescription(dto.getDescription());
        task.setCompleted(false);
        task.setUser(user);
        task.setStatus(TaskStatus.TODO);

        Task savedTask = taskRepository.save(task);


        return convertToResponse(savedTask);
    }


    public List<TaskResponse> getMyTasks() {
        User user = getLoggedUser();
        List<Task> tasks = taskRepository.findByUser(user);


        return tasks.stream()
                .map(this::convertToResponse)
                .toList();
    }

    private TaskResponse convertToResponse(Task task) {

        TaskResponse dto = new TaskResponse();

        dto.setId(task.getId());
        dto.setTitle(task.getTitle());
        dto.setDescription(task.getDescription());
        dto.setCompleted(task.isCompleted());
        dto.setStatus(task.getStatus());

        return dto;
    }

    public TaskResponse updateStatus(Long id, TaskStatus status) {

        User user = getLoggedUser();

        Task task = taskRepository.findByIdAndUser(id, user)
                .orElseThrow(() -> new ResourceNotFoundException("Task not found"));

        task.setStatus(status);

        Task updatedTask = taskRepository.save(task);

        return convertToResponse(updatedTask);
    }

    public TaskResponse getById(Long id) {

        User user = getLoggedUser();

        Task task = taskRepository.findByIdAndUser(id, user)
                .orElseThrow(() -> new ResourceNotFoundException("Task not found"));

        return convertToResponse(task);

    }


    public TaskResponse update(Long id, UpdateTaskRequest dto) {

        User user = getLoggedUser();

        Task task = taskRepository.findByIdAndUser(id, user)
                .orElseThrow(() -> new ResourceNotFoundException("Task not found"));

        task.setTitle(dto.getTitle());
        task.setDescription(dto.getDescription());

        Task updatedTask = taskRepository.save(task);

        return convertToResponse(updatedTask);
    }


    public void delete(Long id) {

        User user = getLoggedUser();

        Task task = taskRepository.findByIdAndUser(id, user)
                .orElseThrow(() -> new ResourceNotFoundException("Task not found"));

        taskRepository.delete(task);
    }
}



