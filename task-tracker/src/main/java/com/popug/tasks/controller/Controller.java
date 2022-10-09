package com.popug.tasks.controller;

import com.popug.tasks.kafkaMessages.AssignedTaskMessage;
import com.popug.tasks.dto.CreatedTask;
import com.popug.tasks.dto.ReassignedTasks;
import com.popug.tasks.kafkaMessages.CreatedTaskMessage;
import com.popug.tasks.producer.CreationProducer;
import com.popug.tasks.producer.ReassignedProducer;
import com.popug.tasks.model.Task;
import com.popug.tasks.model.User;
import com.popug.tasks.repository.TaskService;
import com.popug.tasks.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/tasks")
public class Controller {

    private final CreationProducer creationProducer;

    private final ReassignedProducer reassignedProducer;

    private final TaskService taskService;

    private final UserRepository userRepository;

    @PostMapping(value = "/create")
    public void send(@RequestBody CreatedTask createdTask){
        Task task = new Task();
        task.setId(UUID.randomUUID().toString());
        task.setDescription(createdTask.getDescription());
        List<String> userIds = StreamSupport.stream(userRepository.findAll().spliterator(), false)
                .map(User::getPublicUserId)
                .collect(Collectors.toList());
        String randomUserId = userIds.get(new Random().nextInt(userIds.size()));
        task.setPublicUserId(randomUserId);
        taskService.save(task);
        creationProducer.send(CreatedTaskMessage.builder()
                .id(task.getId())
                .description(task.getDescription())
                .publicUserId(task.getPublicUserId())
                .build());
    }

    @PostMapping(value = "/reassign")
    public void send(@RequestBody ReassignedTasks reassignedTasks){
        List<String> publicUserIds = StreamSupport.stream(userRepository.findAll().spliterator(), false)
                .map(User::getPublicUserId)
                .collect(Collectors.toList());
        List<Task> tasks = reassignedTasks.getIds().stream()
                .map(id -> taskService.updatePublicUserIdById(publicUserIds
                        .get(new Random().nextInt(publicUserIds.size())), id))
                .collect(Collectors.toList());
        tasks.forEach(task -> reassignedProducer.send(AssignedTaskMessage.builder()
                .id(task.getId())
                .description(task.getDescription())
                .publicUserId(task.getPublicUserId())
                .build()));
    }
}