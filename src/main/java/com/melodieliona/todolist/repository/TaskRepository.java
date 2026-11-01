package com.melodieliona.todolist.repository;

import java.util.List;

import org.springframework.boot.data.autoconfigure.web.DataWebProperties.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.melodieliona.todolist.dto.TaskResponse;
import com.melodieliona.todolist.entity.Task;

public interface TaskRepository extends JpaRepository<Task, Long> {

    List<TaskResponse> findByCompleted(boolean completed);

    List<TaskResponse> findByTitle(String title);

    @Query("SELECT t FROM Task t WHERE t.completed = :completed")
    List<TaskResponse> findCompletedTasks(@Param("complete") boolean completed);

    // New method
    Page<Task> findByCompleted(boolean completed, Pageable pageable);

    Page<Task> findByTitle(String title, Pageable pageable);

    @Query("SELECT t FROM Task t WHERE t.completed = :completed")
    Page<Task> findCompletedTasks(@Param("complete") boolean completed, Pageable pageable);

}
