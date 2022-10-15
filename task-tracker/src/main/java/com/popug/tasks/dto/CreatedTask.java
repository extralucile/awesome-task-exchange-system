package com.popug.tasks.dto;

import lombok.Value;

@Value
public class CreatedTask {

    public CreatedTask() {
        this.description = null;
    }

    private final String description;
}
