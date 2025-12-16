package com.example.cloverville_project;

import java.util.ArrayList;
import java.util.List;

public class CommunalTaskList {

    private List<CommunalTask> tasks = new ArrayList<>();

    public CommunalTaskList() {}

    public void addTask(CommunalTask task) {
        tasks.add(task);
    }

    public void removeTask(CommunalTask task) {
        tasks.remove(task);
    }

    public List<CommunalTask> getAllTasks() {
        return tasks;
    }
    public void clear() {
        tasks.clear();   // or offers.clear(), tasks.clear(), actions.clear()
    }


    public CommunalTask findByName(String name) {
        for (CommunalTask task : tasks) {
            if (task.getName().equalsIgnoreCase(name)) {
                return task;
            }
        }
        return null;
    }

    public boolean exists(String name) {
        return findByName(name) != null;
    }

    public List<CommunalTask> getTasksByPointValue(int points) {
        List<CommunalTask> result = new ArrayList<>();
        for (CommunalTask t : tasks) {
            if (t.getPersonalPoints() == points) {
                result.add(t);
            }
        }
        return result;
    }
}
