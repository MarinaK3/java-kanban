package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Epic extends Task {
    private List<SubTask> subTasks = new ArrayList<>();

    public Epic(String name, String description) {
        super(name, description);
    }

    public List<SubTask> getSubTasks() {
        return subTasks;
    }

    public void addSubTask(SubTask subTask) {
        subTasks.add(subTask);
    }

    public void updateStatus() {
        if (subTasks.isEmpty()) {
            status = Status.NEW;
            return;
        }
        boolean allNew = true;
        boolean allDone = true;
        for (SubTask subTask : subTasks) {
            Status subTaskStatus = subTask.getStatus();
            if (subTaskStatus != Status.NEW) {
                allNew = false;
            }
            if (subTaskStatus != Status.DONE) {
                allDone = false;
            }
            if (!allNew && !allDone) {
                status = Status.IN_PROGRESS;
                return;
            }
        }

        if (allNew) {
            status = Status.NEW;
        } else if (allDone) {
            status = Status.DONE;
        } else {
            status = Status.IN_PROGRESS;
        }

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Epic epic = (Epic) o;
        return Objects.equals(subTasks, epic.subTasks);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), subTasks);
    }
}
