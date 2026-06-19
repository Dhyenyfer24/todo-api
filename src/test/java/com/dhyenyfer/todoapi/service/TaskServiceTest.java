package com.dhyenyfer.todoapi.service;

import com.dhyenyfer.todoapi.dto.TaskResponse;
import com.dhyenyfer.todoapi.dto.request.TaskRequest;
import com.dhyenyfer.todoapi.entity.Task;
import com.dhyenyfer.todoapi.entity.User;
import com.dhyenyfer.todoapi.enums.TaskStatus;
import com.dhyenyfer.todoapi.exception.ResourceNotFoundException;
import com.dhyenyfer.todoapi.repository.TaskRepository;
import com.dhyenyfer.todoapi.security.CustomUserDetails;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TaskServiceTest {

    @Mock
    private TaskRepository taskRepository;

    @InjectMocks
    private TaskService taskService;

    @Test
    void shouldCreateTaskSuccessfully() {

        User user = new User();
        user.setId(1L);
        user.setName("Dhyenyfer");
        user.setEmail("dhyenyfer@email.com");

        CustomUserDetails userDetails = new CustomUserDetails(user);

        UsernamePasswordAuthenticationToken authentication =
                new UsernamePasswordAuthenticationToken(
                        userDetails,
                        null,
                        userDetails.getAuthorities()
                );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        TaskRequest request = new TaskRequest();
        request.setTitle("Study Spring");
        request.setDescription("Review Spring Security");

        Task savedTask = new Task();
        savedTask.setId(1L);
        savedTask.setTitle("Study Spring");
        savedTask.setDescription("Review Spring Security");
        savedTask.setCompleted(false);
        savedTask.setStatus(TaskStatus.TODO);
        savedTask.setUser(user);

        when(taskRepository.save(any(Task.class)))
                .thenReturn(savedTask);

        TaskResponse response = taskService.create(request);

        assertEquals(1L, response.getId());
        assertEquals("Study Spring", response.getTitle());
        assertEquals("Review Spring Security", response.getDescription());
        assertEquals(false, response.isCompleted());
        assertEquals(TaskStatus.TODO, response.getStatus());
    }

    @Test
    void shoulUpdateTaskStatusSucessfully(){

        User user = new User();
        user.setId(1L);
        user.setName("Luiza");
        user.setEmail("Luiza@email.com");

        CustomUserDetails userDetails = new CustomUserDetails(user);

        UsernamePasswordAuthenticationToken authentication =
                new UsernamePasswordAuthenticationToken(
                        userDetails,
                        null,
                        userDetails.getAuthorities()
                );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        Task task = new Task();
        task.setId(1L);
        task.setTitle("Study Spring");
        task.setDescription("Review Spring Security");
        task.setCompleted(false);
        task.setStatus(TaskStatus.TODO);
        task.setUser(user);

        Task updatedTask = new Task();
        updatedTask.setId(1L);
        updatedTask.setTitle("Study Spring");
        updatedTask.setDescription("Review Spring Security");
        updatedTask.setCompleted(false);
        updatedTask.setStatus(TaskStatus.DONE);
        updatedTask.setUser(user);

        when (taskRepository.findByIdAndUser(1L,user))
                .thenReturn(Optional.of(updatedTask));

        when(taskRepository.save(any(Task.class)))
                .thenReturn(updatedTask);

        TaskResponse response = taskService.updateStatus(1L,TaskStatus.DONE);

        assertEquals(1L, response.getId());
        assertEquals("Study Spring", response.getTitle());
        assertEquals("Review Spring Security", response.getDescription());
        assertEquals(TaskStatus.DONE, response.getStatus());

        verify(taskRepository).findByIdAndUser(1L, user);
        verify(taskRepository).save(any(Task.class));
    }

    @Test
    void shouldThrowExceptionWhenTaskNotFound() {

        User user = new User();
        user.setId(1L);
        user.setName("Luiza");
        user.setEmail("Luiza@email.com");

        CustomUserDetails userDetails = new CustomUserDetails(user);

        UsernamePasswordAuthenticationToken authentication =
                new UsernamePasswordAuthenticationToken(
                        userDetails,
                        null,
                        userDetails.getAuthorities()
                );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        when(taskRepository.findByIdAndUser(999L, user))
                .thenReturn(Optional.empty());

        assertThrows(
                ResourceNotFoundException.class,
                () -> taskService.updateStatus(999L, TaskStatus.DONE)
        );
    }
}