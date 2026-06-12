package com.dhyenyfer.todoapi.service;

import com.dhyenyfer.todoapi.dto.TaskResponse; // 💡 Import correto do seu DTO
import com.dhyenyfer.todoapi.dto.request.TaskRequest;
import com.dhyenyfer.todoapi.entity.Task;
import com.dhyenyfer.todoapi.entity.User;
import com.dhyenyfer.todoapi.enums.TaskStatus;
import com.dhyenyfer.todoapi.repository.TaskRepository;
import com.dhyenyfer.todoapi.repository.UserRepository;
import com.dhyenyfer.todoapi.security.CustomUserDetails;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskService {

    private final TaskRepository taskRepository;
    private final UserRepository userRepository;

    public TaskService(TaskRepository taskRepository, UserRepository userRepository) {
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
    }

    // 🔑 pega usuário logado
    private User getLoggedUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails userDetails = (CustomUserDetails) auth.getPrincipal();
        return userDetails.getUser();
    }

    // CREATE TASK
    public TaskResponse create(TaskRequest dto) { // 💡 Ajustado para TaskResponse
        User user = getLoggedUser();

        Task task = new Task();
        task.setTitle(dto.getTitle());
        task.setDescription(dto.getDescription());
        task.setCompleted(false); // Verifique se na sua Entity o método é setCompleted ou setComplete
        task.setUser(user);
        task.setStatus (TaskStatus.TODO);

        Task savedTask = taskRepository.save(task);

        // 🔄 Transforma a Entity salva no DTO de resposta limpo
        return convertToResponse(savedTask);
    }

    // LISTAR TASKS DO USUÁRIO
    public List<TaskResponse> getMyTasks() { // 💡 Ajustado para List<TaskResponse>
        User user = getLoggedUser();
        List<Task> tasks = taskRepository.findByUser(user);

        // 🔄 Transforma a lista de entidades em uma lista de DTOs limpos
        return tasks.stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    // 🔄 Método auxiliar para fazer o mapeamento (Converter Entity -> TaskResponse)
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

        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task não encontrada"));

        task.setStatus(status);

        Task updatedTask = taskRepository.save(task);

        return convertToResponse(updatedTask);
    }
}