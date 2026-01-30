package tom.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
/**
 * Represents a task scheduled between two dates.
 */
public class Event extends Task {
    /** Start date for the event. */
    private final LocalDate from;
    /** End date for the event. */
    private final LocalDate to;

    /**
     * Creates an event task with the provided description and dates.
     *
     * @param description Task description.
     * @param from Start date in ISO-8601 format (yyyy-MM-dd).
     * @param to End date in ISO-8601 format (yyyy-MM-dd).
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = LocalDate.parse(from);
        this.to = LocalDate.parse(to);
    }

    public LocalDate getFrom() {
        return from;
    }

    public LocalDate getTo() {
        return to;
    }

    /**
     * Returns the display string for this event task.
     *
     * @return Event display string.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: "
                     + from.format(DateTimeFormatter.ofPattern("MMM dd yyyy"))
                     + " to: "
                     + to.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
    }

    /**
     * Returns whether this event is equal to another object.
     *
     * @param obj Object to compare.
     * @return True if the other object is an equivalent event.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Event other = (Event) obj;
        return super.equals(obj) && from.equals(other.from) && to.equals(other.to);
    }

}
