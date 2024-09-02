package com.springapi.sample_api.entities;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

//import javax.persistence.*;
import jakarta.persistence.*;
import java.util.Date;

@Table(name = "tasks")
@Entity
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private Integer id;

    @Column(unique = true, length = 200, nullable = false)
    private String name;

    @Lob
    private String description;

    @Column(nullable = false, columnDefinition = "varchar (20) not null default 'PENDING'")
    @Enumerated(EnumType.STRING)
    private TaskStatusEnum status;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "due_date")
    private Date dueDate;

    @CreationTimestamp
    @Column(name = "created_at")
    private Date createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private Date updatedAt;

    // Getters and Setters and toString()
    public Task setName(String name) {
        this.name = name;
        return this;
    }

    public String getName() {
        return name;
    }

    public Task setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Task setStatus(TaskStatusEnum status) {
        this.status = status;
        return this;
    }

    public TaskStatusEnum getStatus() {
        return status;
    }

    public Task setDueDate(Date dueDate) {
        this.dueDate = dueDate;
        return this;
    }

    public Date getDueDate() {
        return dueDate;
    }

}