package com.dhyenyfer.todoapi.service;

import com.dhyenyfer.todoapi.dto.TaskRequestDTO;
import com.dhyenyfer.todoapi.dto.TaskResponseDTO;
import com.dhyenyfer.todoapi.exception.ResourceNotFoundException;
import com.dhyenyfer.todoapi.model.Task;
import com.dhyenyfer.todoapi.repository.TaskRepository;
import org.springframework.stereotype.Service;
import com.dhyenyfer.todoapi.exception.ResourceNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskService {

    private final TaskRepository repository;

    public TaskService(TaskRepository repository) {
        this.repository = repository;
    }

    // CREATE
    public TaskResponseDTO create(TaskRequestDTO dto) {
        Task task = new Task();
        task.setTitle(dto.getTitle());
        task.setDescription(dto.getDescription());
        task.setCompleted(false);

        return toDTO(repository.save(task));
    }

    // LIST ALL
    public List<TaskResponseDTO> findAll() {
        return repository.findAll()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    // FIND BY ID
    public TaskResponseDTO findById(Long id) {
        Task task = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Task não encontrada"));

        return toDTO(task);
    }

    // UPDATE
    public TaskResponseDTO update(Long id, TaskRequestDTO dto) {
        Task task = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Task não encontrada"));

        task.setTitle(dto.getTitle());
        task.setDescription(dto.getDescription());
        ;
        return toDTO(repository.save(task));
    }

    // DELETE
    public void delete(Long id) {
        Task task = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Task não encontrada"));

        repository.delete(task);
    }

    // ENTITY -> DTO
    private TaskResponseDTO toDTO(Task task) {
        return new TaskResponseDTO(
                task.getId(),
                task.getTitle(),
                task.getDescription(),
                task.isCompleted()
        );
    }

    public Task completeTask(Long id) {

        Task task = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Task não encontrada"));

        task.setCompleted(true);

        return repository.save(task);
    }

    public List<Task> findByCompleted(boolean completed) {
        return repository.findByCompleted(completed);
    }
}