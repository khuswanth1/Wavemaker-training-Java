package com.Wm.Todo.pack;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Objects;

public class Task {
    private int taskId;
    private int userId;
    private String taskName;
    private String priority;
    private LocalDateTime dueDateTime;
    private boolean completed;
    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public LocalDateTime getDueDateTime() {
        return getDueDateTime();
    }

    public void setDueDateTime(LocalDateTime dueDateTime) {
        this.dueDateTime = dueDateTime;
    }



    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Task task = (Task) object;
        return taskId == task.taskId && userId == task.userId && Objects.equals(taskName, task.taskName) && Objects.equals(priority, task.priority) && Objects.equals(dueDateTime, task.dueDateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(taskId, userId, taskName, priority, dueDateTime);
    }

    @Override
    public String toString() {
        return "Task{" +
                "taskId=" + taskId +
                ", userId=" + userId +
                ", taskName='" + taskName + '\'' +
                ", priority='" + priority + '\'' +
                ", dueDateTime=" + dueDateTime +
                ", completed=" + completed +
                '}';
    }
}