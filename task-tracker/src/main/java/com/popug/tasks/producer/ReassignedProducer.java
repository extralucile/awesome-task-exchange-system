package com.popug.tasks.producer;

import com.popug.tasks.kafkaMessages.AssignedTaskMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReassignedProducer {

    @Value("reassign")
    private String topicName;

    private final KafkaTemplate<String, AssignedTaskMessage> reassigningKafkaTemplate;

    public void send(AssignedTaskMessage message){
        reassigningKafkaTemplate.send(topicName, message);
    }
}