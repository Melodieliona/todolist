package com.melodieliona.todolist.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record TaskRequest(
        @NotBlank(message = "Title is mandatory") @Size(min = 3, max = 30, message = "Title must havebetween 3 and 30 characters") String title,

        @Size(min = 3, max = 100, message = "Title must havebetween 3 and 50 characters") String description,

        Boolean completed) {
}
