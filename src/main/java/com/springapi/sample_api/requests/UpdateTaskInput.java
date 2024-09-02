package com.springapi.sample_api.requests;

import com.springapi.sample_api.entities.TaskStatusEnum;
import java.util.*;
public record UpdateTaskInput(TaskStatusEnum status, Date dueDate) {
    
}
