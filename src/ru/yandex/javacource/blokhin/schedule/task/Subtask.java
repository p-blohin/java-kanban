package ru.yandex.javacource.blokhin.schedule.task;

public class Subtask extends Task {

    private Integer epicId;

    public Subtask() {
        super();
    }

    public Subtask(String name, String description, Integer epicId) {
        super(name, description);
        this.epicId = epicId;
    }

    public Subtask(String name, String description, TaskStatus status) {
        super(name, description, status);
    }

    public Subtask(String name, String description, Integer id, Integer epicId) {
        super(name, description, id);
        this.epicId = epicId;
    }
    public Subtask(String name, String description, TaskStatus status, Integer id, Integer epicId) {
        super(name, description, status, id);
        this.epicId = epicId;
    }

    public Integer getEpicId() {
        return epicId;
    }

    public void setEpicId(int epicId) {
        this.epicId = epicId;
    }

    @Override
    public String toString() {
        return "ru.yandex.javacource.blokhin.schedule.task.Subtask{" +
                "name='" + super.getName() + '\'' +
                ", description='" + super.getDescription() + '\'' +
                ", id=" + super.getId() +
                ", status=" + super.getStatus() +
                ", epicId=" + epicId +
                '}';
    }
}
