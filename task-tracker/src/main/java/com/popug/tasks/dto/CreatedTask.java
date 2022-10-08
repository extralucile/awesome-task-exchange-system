package com.popug.tasks.dto;

import lombok.Value;

@Value
public class CreatedTask {

    private final String userPublicId;

    private final String description;
}
