package com.dhyenyfer.todoapi.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class UpdateTaskRequest {


    @NotBlank(message = "Title is required")
    @Size(max = 100, message = "Title must have at most 100 characters")
    private String title;

    @Size(max = 500, message = "Description must have at most 500 characters")
    private String description;

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
}
