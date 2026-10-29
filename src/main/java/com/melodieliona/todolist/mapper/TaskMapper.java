package com.melodieliona.todolist.mapper;

import org.springframework.stereotype.Component;

import com.melodieliona.todolist.dto.TaskRequest;
import com.melodieliona.todolist.dto.TaskResponse;
import com.melodieliona.todolist.entity.Task;

@Component
public class TaskMapper {
    public Task toEntity(TaskRequest request) {
        return Task.builder()
                .title(request.title())
                .description(request.description())
                .completed(request.completed() != null ? request.completed() : false)
                .build();
    }

    public TaskResponse toResponse(Task task) {
        return TaskResponse.builder()
                .id(task.getId())
                .title(task.getTitle())
                .description(task.getDescription())
                .completed(task.getCompleted())
                .createdAt(task.getCreatedAt())
                .build();
    }

    public void updateEntityFromReqest(Task task, TaskRequest request) {
        task.setTitle(request.title());
        task.setDescription(request.description());
        task.setCompleted(request.completed() != null ? request.completed() : task.getCompleted());
    }
}
