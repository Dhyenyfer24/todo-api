package com.dhyenyfer.todoapi.dto;

public class TaskResponseDTO {

    private Long id;
    private String title;
    private String description;
    private boolean complete;

    public TaskResponseDTO(Long id, String title, String description, boolean completed) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.complete = completed;
    }

    public Long getId() {
        return id;
    }

    public boolean isCompleted() {
        return complete;
    }

    public String getDescription() {
        return description;
    }

    public String getTitle() {
        return title;
    }
}