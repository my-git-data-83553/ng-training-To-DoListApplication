package com.sunbeam.service;

import com.sunbeam.entity.Task;
import com.sunbeam.dao.TaskRepository;
import com.sunbeam.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskRepository taskDao;

    @Override
    public List<Task> getAllTasks() {
        return taskDao.findAll();
    }

    @Override
    public Task createTask(Task task) {
        return taskDao.save(task);
    }

    @Override
    public Task updateTask(Long id, Task taskDetails) {
        Task task = taskDao.findById(id)
            .orElseThrow(() -> new RuntimeException("Task not found"));
        task.setAssignedTo(taskDetails.getAssignedTo());
        task.setStatus(taskDetails.getStatus());
        task.setDueDate(taskDetails.getDueDate());
        task.setPriority(taskDetails.getPriority());
        task.setComments(taskDetails.getComments());
        return taskDao.save(task);
    }

    @Override
    public void deleteTask(Long id) {
        Task task = taskDao.findById(id)
            .orElseThrow(() -> new RuntimeException("Task not found"));
        taskDao.delete(task);
    }
}
