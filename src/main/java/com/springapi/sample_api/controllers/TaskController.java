package com.springapi.sample_api.controllers;
import java.util.List;
import java.util.Optional;
import org.springframework.web.bind.annotation.RestController;

import com.springapi.sample_api.entities.Task;
import com.springapi.sample_api.requests.CreateTaskInput;
import com.springapi.sample_api.requests.UpdateTaskInput;
import com.springapi.sample_api.services.TaskService;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
public class TaskController {
    public TaskService taskService;

    public TaskController(TaskService task) {
        this.taskService = task;
    }

    @PostMapping("/tasks")
    public ResponseEntity<Task> createTask(@RequestBody CreateTaskInput createTaskInput) {
        Task taskCreated = taskService.create(createTaskInput.toTask());

        return new ResponseEntity<>(taskCreated,HttpStatus.CREATED);
    }
    @GetMapping("/tasks")
    public ResponseEntity<List<Task>> allTasks() {
        List<Task> tasks = taskService.findAll();
        return new ResponseEntity<>(tasks, HttpStatus.OK);
    }

    @GetMapping("/tasks/{id}")
    public ResponseEntity<Task> oneTask(@PathVariable int id) {
        Optional<Task> task = taskService.findById(id);
        if(task.isPresent()){
            return new ResponseEntity<>(task.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    
    @PatchMapping("/tasks/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable int id , @RequestBody UpdateTaskInput updateTaskInput){
        Optional<Task> task = taskService.findById(id);
        if(!task.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Task taskToUpdate = task.get();
        taskToUpdate.setStatus(updateTaskInput.status());
        taskToUpdate.setDueDate(updateTaskInput.dueDate());

        Task updatedTask = taskService.update(taskToUpdate);

        return new ResponseEntity<>(updatedTask, HttpStatus.OK);
    }
    
    @DeleteMapping("/tasks/{id}")
    public ResponseEntity<Task> deleteTask(@PathVariable int id){
        Optional<Task> task = taskService.findById(id);
        if(!task.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        taskService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
