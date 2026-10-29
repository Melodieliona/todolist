package com.melodieliona.todolist.dto;

import java.time.LocalDateTime;

import lombok.Builder;

@Builder
public record TaskResponse(Long id, String title, String description, Boolean completed, LocalDateTime createdAt) {
}
