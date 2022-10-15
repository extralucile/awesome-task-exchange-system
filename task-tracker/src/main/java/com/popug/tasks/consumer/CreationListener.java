package com.popug.tasks.consumer;

import com.popug.tasks.kafkaMessages.CreatedTaskMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class CreationListener {

    @KafkaListener(topics = "create", groupId = "group_id")
    public void consume(ConsumerRecord<String, CreatedTaskMessage> payload){
        log.info("Value: {}", payload.value());
    }
}