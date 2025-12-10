package com.example.cloverville_project;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TaskLogEntry {

    private final Resident resident;
    private final CommunalTask task;
    private final LocalDateTime time;

    public TaskLogEntry(Resident resident, CommunalTask task) {
        this.resident = resident;
        this.task = task;
        this.time = LocalDateTime.now();
    }

    public Resident getResident() { return resident; }
    public CommunalTask getTask() { return task; }

    @Override
    public String toString() {
        return time.format(DateTimeFormatter.ofPattern("HH:mm dd/MM")) +
                " - " + resident.getName() +
                " completed: " + task.getName() +
                " (+" + task.getPersonalPoints() + " personal points)";
    }
}
