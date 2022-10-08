package com.popug.tasks.controller;

import com.popug.tasks.dto.CreatedTask;
import com.popug.tasks.dto.ReassignedTasks;
import com.popug.tasks.producer.CreationProducer;
import com.popug.tasks.producer.ReassignedProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/tasks")
public class Controller {

    private final CreationProducer creationProducer;

    private final ReassignedProducer reassignedProducer;

    @PostMapping(value = "/create")
    public void send(@RequestBody CreatedTask createdTask){
        creationProducer.send(createdTask);
    }

    @PostMapping(value = "/reassign")
    public void send(@RequestBody ReassignedTasks reassignedTasks){
        reassignedProducer.send(reassignedTasks);
    }
}