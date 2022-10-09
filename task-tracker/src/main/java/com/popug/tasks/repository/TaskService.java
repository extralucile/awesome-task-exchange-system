package com.popug.tasks.repository;

import com.popug.tasks.model.Task;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;

    public Task updatePublicUserIdById(String publicUserId, String id) {
        Task task = taskRepository.findById(id).get();
        task.setPublicUserId(publicUserId);
        return taskRepository.save(task);
    }

    public Task save(Task task) {
        return taskRepository.save(task);
    }

}