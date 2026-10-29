package com.melodieliona.todolist.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.melodieliona.todolist.dto.TaskRequest;
import com.melodieliona.todolist.dto.TaskResponse;
import com.melodieliona.todolist.entity.Task;
import com.melodieliona.todolist.exception.TaskNotFoundException;
import com.melodieliona.todolist.mapper.TaskMapper;
import com.melodieliona.todolist.repository.TaskRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class TaskService {

    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;

    public TaskService(TaskRepository taskRepository, TaskMapper taskMapper) {
        this.taskRepository = taskRepository;
        this.taskMapper = taskMapper;
    }

    public List<Task> getAllTask() {
        return taskRepository.findAll();
    }

    public TaskResponse getTaskById(Long id) {
        Task retrievedTask = taskRepository.findById(id).orElseThrow(() -> new TaskNotFoundException(id));
        return taskMapper.toResponse(retrievedTask);
    }

    public TaskResponse createTask(TaskRequest task) {
        Task entityTask = taskMapper.toEntity(task);
        Task savedTask = taskRepository.save(entityTask);
        return taskMapper.toResponse(savedTask);
    }

    public TaskResponse updateTask(Long id, TaskRequest updatedTask) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException(id));

        taskMapper.updateEntityFromReqest(task, updatedTask);
        return taskMapper.toResponse(taskRepository.save(task));

    }

    public void deleteTask(Long id) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException(id));
        taskRepository.delete(task);
    }

    public List<TaskResponse> getTasksByCompletionStatus(boolean status) {
        return taskRepository.findByCompleted(status);
    }

    public List<TaskResponse> searchTaskByTitleId(String title) {
        return taskRepository.findByTitle(title);
    }
}
