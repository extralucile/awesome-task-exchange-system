package com.popug.tasks.dto;

import lombok.Value;

@Value
public class CreatedTask {

    public CreatedTask() {
        this.userPublicId = null;
        this.description = null;
    }

    private final String userPublicId;

    private final String description;
}
