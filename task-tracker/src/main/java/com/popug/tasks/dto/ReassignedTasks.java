package com.popug.tasks.dto;

import lombok.Value;
import java.util.ArrayList;
import java.util.List;

@Value
public class ReassignedTasks {

    public ReassignedTasks() {
        this.ids = new ArrayList<>();
    }

    private final List<String> ids;
}
