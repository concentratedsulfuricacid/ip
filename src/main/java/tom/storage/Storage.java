package tom.storage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import tom.TomException;
import tom.task.Deadline;
import tom.task.Event;
import tom.task.Task;
import tom.task.Todo;

public class Storage {
    private final Path filePath;

    public Storage() {
        this.filePath = Paths.get("data", "tasks.txt");
    }

    public Storage(String filePath) {
        this.filePath = Paths.get(filePath);
    }

    public void ensureStorageExists() throws IOException {
        Files.createDirectories(filePath.getParent());
        if (!Files.exists(filePath)) {
            Files.createFile(filePath);
        }
    }

    public static Path getStoragePath() {
        String userDir = System.getProperty("user.dir");
        Path path = Paths.get(userDir, "data", "tasks.txt");
        // System.out.println("Storage path: " + path);
        return path;
    }

    public static String encode(Task task) throws TomException {
        if (task instanceof Todo) {
            return "T | " + (task.isDone() ? "1" : "0") + " | " + task.getDescription();
        } else if (task instanceof Deadline) {
            Deadline deadline = (Deadline) task;
            return "D | " + (task.isDone() ? "1" : "0") + " | " + task.getDescription()
                    + " | " + deadline.getBy();
        } else if (task instanceof Event) {
            Event event = (Event) task;
            return "E | " + (task.isDone() ? "1" : "0") + " | " + task.getDescription()
                    + " | " + event.getFrom() + " | " + event.getTo();
        } else {
            throw new TomException("Unknown Task Type");
        }
    }

    public static Task decode(String line) throws TomException {
        try {
            String[] parts = line.split(" \\| ");
            String type = parts[0];
            boolean isDone = parts[1].equals("1");
            String description = parts[2];

            switch (type) {
                case "T":
                    Todo todo = new Todo(description);
                    if (isDone) {
                        todo.markAsDone();
                    }
                    return todo;
                case "D":
                    String by = parts[3];
                    Deadline deadline = new Deadline(description, by);
                    if (isDone) {
                        deadline.markAsDone();
                    }
                    return deadline;
                case "E":
                    String from = parts[3];
                    String to = parts[4];
                    Event event = new Event(description, from, to);
                    if (isDone) {
                        event.markAsDone();
                    }
                    return event;
                default:
                    throw new TomException("Unknown Task Type");
            }
        } catch (Exception e) {
            throw new TomException("Error decoding task: " + e.getMessage());
        }
    }

    public static void updateStorage(List<Task> tasks) {
        try {
            Path filePath = getStoragePath();
            List<String> lines = new ArrayList<>();
            for (Task task : tasks) {
                lines.add(encode(task));
            }
            Files.write(filePath, lines);
            // System.out.println("Storage updated successfully.");
        } catch (IOException e) {
            System.out.println("Error updating storage: " + e.getMessage());
        } catch (TomException e) {
            System.out.println("Error encoding tasks: " + e.getMessage());
        }
    }

    public List<Task> load() throws TomException {
        List<Task> tasks = new ArrayList<>();
        try {
            ensureStorageExists();
            List<String> lines = Files.readAllLines(filePath);
            tasks.addAll(lines.stream().map(line -> {
                try {
                    return decode(line);
                } catch (TomException e) {
                    System.out.println("Error decoding line: " + line + " - " + e.getMessage());
                    System.out.println("Skipping task");
                    return null;
                }
            }).filter(task -> task != null).toList());

        } catch (IOException e) {
            System.out.println("Error loading storage: " + e.getMessage());
        }
        return tasks;
    }

    public static void main(String[] args) {
        getStoragePath();
        getStoragePath();
    }
}
