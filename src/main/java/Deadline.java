public class Deadline extends Task {
    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

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