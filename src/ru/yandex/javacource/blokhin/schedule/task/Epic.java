package ru.yandex.javacource.blokhin.schedule.task;

import java.util.ArrayList;

public class Epic extends Task {
    private ArrayList<Integer> subtasksId = new ArrayList<>();

    public Epic() {
        super();
    }

    public Epic(String name, String description) {
        super(name, description);
    }

    public Epic(String name, String description, TaskStatus status) {
        super(name, description, status);
    }

    public Epic(String name, String description, int id) {
        super(name, description, id);
    }

    public Epic(String name, String description, int id, ArrayList<Integer> subtasksId) {
        super(name, description, id);
        this.subtasksId = subtasksId;
    }

    @Override
    public String toString() {
        return "ru.yandex.javacource.blokhin.schedule.task.Epic{" +
                "name='" + super.getName() + '\'' +
                ", description='" + super.getDescription() + '\'' +
                ", id=" + super.getId() +
                ", status=" + super.getStatus() +
                ", subtasksId=" + subtasksId +
                '}';
    }

    public ArrayList<Integer> getSubtasksId() {
        return subtasksId;
    }

    public void setSubtasksId(ArrayList<Integer> subtasksId) {
        this.subtasksId = subtasksId;
    }

    // метод для unit-теста
    public void removeSubtask(int id) {
        subtasksId.remove(id);
    }

}