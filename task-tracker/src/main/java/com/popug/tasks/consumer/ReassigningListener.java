package com.popug.tasks.consumer;

import com.popug.tasks.dto.ReassignedTasks;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class ReassigningListener {

    @KafkaListener(topics = "reassign", groupId = "group_id")
    public void consume(ConsumerRecord<String, ReassignedTasks> payload){
        log.info("key: {}", payload.key());
        log.info("Headers: {}", payload.headers());
        log.info("Partion: {}", payload.partition());
        log.info("Order: {}", payload.value());
    }

}