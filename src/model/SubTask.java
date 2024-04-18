package model;

public class SubTask extends Task {
    private Epic epic;

    public SubTask(String name, Status status, String description, Epic epic) {
        super(name, status, description);
        this.epic = epic;
    }

    public Epic getEpic() {
        return epic;
    }

    @Override
    public String toString() {
        return "SubTask{" +
                "id=" + getId() +
                ", name='" + getName() + '\'' +
                ", status=" + getStatus() +
                ", description='" + getDescription() + '\'' +
                '}';
    }
}
