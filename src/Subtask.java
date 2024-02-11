public class Subtask extends Task {

    private int epicId;

    public Subtask(String name, String description, int id, TaskStatus status) {
        super(name, description, id, status);
    }

    public Subtask(String name, String description, int id) {
        super(name, description, id);
    }

    public int getEpicId() {
        return epicId;
    }

    public void setEpicId(int epicId) {
        this.epicId = epicId;
    }

    @Override
    public String toString() {
        return "Subtask{" +
                "name='" + super.getName() + '\'' +
                ", description='" + super.getDescription() + '\'' +
                ", id=" + super.getId() +
                ", status=" + super.getStatus() +
                ", epicId=" + epicId +
                '}';
    }
}
