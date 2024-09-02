package com.springapi.sample_api.requests;

import com.springapi.sample_api.entities.Task;
import com.springapi.sample_api.entities.TaskStatusEnum;
import java.util.Date;

public record CreateTaskInput(String name, String description, TaskStatusEnum status, Date dueDate) {
    public Task toTask() {
        Task task = new Task();

        task.setName(name).setDescription(description).setStatus(status).setDueDate(dueDate);

        return task;
    }
}
