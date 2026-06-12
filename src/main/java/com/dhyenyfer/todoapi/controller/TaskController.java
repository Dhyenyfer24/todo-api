package com.dhyenyfer.todoapi.controller;
import com.dhyenyfer.todoapi.dto.TaskResponse; // 💡 Import do seu DTO/Response
import com.dhyenyfer.todoapi.dto.request.TaskRequest;
import com.dhyenyfer.todoapi.service.TaskService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.dhyenyfer.todoapi.enums.TaskStatus;
import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    // Criar uma Task
    @PostMapping
    public ResponseEntity<TaskResponse> create(@RequestBody TaskRequest dto) {
        // O service agora entrega o TaskResponse perfeitamente mapeado
        TaskResponse response = taskService.create(dto);

        // Retorna HTTP 201 (Created) com o objeto limpo no corpo
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // Listar as Tasks do usuário logado
    @GetMapping
    public ResponseEntity<List<TaskResponse>> getMyTasks() {
        List<TaskResponse> response = taskService.getMyTasks();

        // Retorna HTTP 200 (OK) com a lista limpa
        return ResponseEntity.ok(response);
    }
    @PatchMapping("/{id}/status")
    public ResponseEntity<TaskResponse> updateStatus(
            @PathVariable Long id,
            @RequestParam TaskStatus status
    ) {
        TaskResponse response = taskService.updateStatus(id, status);

        return ResponseEntity.ok(response);
    }
}