package tom.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task with a due date.
 */
public class Deadline extends Task {
    /** Due date for the deadline. */
    private final LocalDate by;

    /**
     * Creates a deadline task with the provided description and due date.
     *
     * @param description Task description.
     * @param by Due date in ISO-8601 format (yyyy-MM-dd).
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = LocalDate.parse(by);
    }

    /**
     * Returns the due date.
     *
     * @return Due date for the deadline.
     */
    public LocalDate getBy() {
        return by;
    }

    /**
     * Returns the display string for this deadline task.
     *
     * @return Deadline display string.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
    }

    /**
     * Returns whether this deadline is equal to another object.
     *
     * @param obj Object to compare.
     * @return True if the other object is an equivalent deadline.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Deadline other = (Deadline) obj;
        return super.equals(obj) && by.equals(other.by);
    }
}
