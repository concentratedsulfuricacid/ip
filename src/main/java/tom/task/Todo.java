package tom.task;
/**
 * Represents a todo task.
 */
public class Todo extends Task {
    /**
     * Creates a todo task with the provided description.
     *
     * @param description Task description.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns the display string for this todo task.
     *
     * @return Todo display string.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
    
    /**
     * Returns whether this todo is equal to another object.
     *
     * @param obj Object to compare.
     * @return True if the other object is an equivalent todo.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Todo other = (Todo) obj;
        return super.equals(obj) && getDescription().equals(other.getDescription());
    }
}
