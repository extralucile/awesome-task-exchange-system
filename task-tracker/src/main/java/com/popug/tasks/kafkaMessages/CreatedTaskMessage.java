package com.popug.tasks.kafkaMessages;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class CreatedTaskMessage {

    private final String id;

    private final String description;

    private final String publicUserId;
}
