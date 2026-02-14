package tom.task;

/**
 * Represents a task with a description and completion state.
 */
public class Task {
    /** Task description text. */
    private String description;
    /** Completion status flag. */
    private boolean isDone;

    /**
     * Creates a task with the provided description.
     *
     * @param description Task description.
     */
    public Task(String description) {
        assert description != null : "description should not be null";
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the completion status icon.
     *
     * @return "X" if the task is done, otherwise a blank space.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Returns the task description.
     *
     * @return Task description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns whether the task is marked as done.
     *
     * @return True if the task is done.
     */
    public boolean isDone() {
        return isDone;
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks the task as not done.
     */
    public void unmarkAsDone() {
        this.isDone = false;
    }

    /**
     * Returns the display string for this task.
     *
     * @return Task display string.
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

    /**
     * Returns whether this task is equal to another object.
     *
     * @param obj Object to compare.
     * @return True if the other object is an equivalent task.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Task other = (Task) obj;
        return description.equals(other.description) && isDone == other.isDone;
    }

}
