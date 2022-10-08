package com.popug.tasks.producer;

import com.popug.tasks.dto.ReassignedTasks;
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

    private final KafkaTemplate<String, ReassignedTasks> reassigningKafkaTemplate;

    public void send(ReassignedTasks reassignedTasks){
        reassigningKafkaTemplate.send(topicName, reassignedTasks);
    }

}