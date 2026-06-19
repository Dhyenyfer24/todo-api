package com.dhyenyfer.todoapi.controller;
import com.dhyenyfer.todoapi.dto.TaskResponse;
import com.dhyenyfer.todoapi.dto.request.TaskRequest;
import com.dhyenyfer.todoapi.dto.request.UpdateTaskRequest;
import com.dhyenyfer.todoapi.service.TaskService;
import jakarta.validation.Valid;
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

    @PostMapping
    public ResponseEntity<TaskResponse> create(
            @Valid @RequestBody TaskRequest dto) {

        TaskResponse response = taskService.create(dto);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }


    @GetMapping
    public ResponseEntity<List<TaskResponse>> getMyTasks() {
        List<TaskResponse> response = taskService.getMyTasks();

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

    @GetMapping ("/{id}")
    public ResponseEntity<TaskResponse> getById (@PathVariable Long id) {
        TaskResponse response = taskService.getById(id);

        return ResponseEntity.ok(response);
    }

    @PutMapping ("/{id}")
    public ResponseEntity<TaskResponse> update(
            @PathVariable Long id,
            @Valid @RequestBody UpdateTaskRequest dto
            ) {

        TaskResponse response = taskService.update(id, dto);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping ("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        taskService.delete(id);

        return ResponseEntity.noContent().build();
    }


}