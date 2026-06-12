package com.dhyenyfer.todoapi.dto;

import com.dhyenyfer.todoapi.enums.TaskStatus;

public class TaskResponse {

    private Long id;
    private String title;
    private String description;
    private boolean completed;
    private TaskStatus status;

    public TaskResponse() {
    }

    public TaskResponse(
            Long id,
            String title,
            String description,
            boolean completed,
            TaskStatus status
    ) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.completed = completed;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }
}