package com.popug.tasks.producer;

import com.popug.tasks.kafkaMessages.CreatedTaskMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class CreationProducer {

    @Value("create")
    private String topicName;

    private final KafkaTemplate<String, CreatedTaskMessage> creationKafkaTemplate;

    public void send(CreatedTaskMessage message){
        creationKafkaTemplate.send(topicName, message);
    }
}