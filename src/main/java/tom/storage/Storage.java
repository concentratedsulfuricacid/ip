package tom.storage;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import tom.TomException;
import tom.task.Deadline;
import tom.task.Event;
import tom.task.Task;
import tom.task.Todo;
/**
 * Provides storage utilities for loading and saving tasks.
 */
public class Storage {
    private Path FILE_PATH;
    // private static Path FILE_PATH = Paths.get("data", "tasks.txt");

    /**
     * Creates a storage instance using the default data file path.
     */
    public Storage() {
        this.FILE_PATH = Paths.get("data", "tasks.txt");
    }

    /**
     * Creates a storage instance using the provided file path.
     *
     * @param filepath File path to use for storage.
     */
    public Storage(String filepath) {
        this.FILE_PATH = Paths.get(filepath);
    }

    /**
     * Ensures the storage file and its parent directory exist.
     *
     * @throws IOException If the storage directories or file cannot be created.
     */
    public  void ensureStorageExists() throws IOException {
        Files.createDirectories(FILE_PATH.getParent());
        if (!Files.exists(FILE_PATH)) {
            Files.createFile(FILE_PATH);
        }
    }

    /**
     * Returns the default storage path under the current working directory.
     *
     * @return The default storage path.
     */
    public static Path getStoragePath() {
        String userDir = System.getProperty("user.dir");
        Path path = Paths.get(userDir, "data", "tasks.txt");
        // System.out.println("Storage path: " + path);
        return path;
    }

    /**
     * Returns the encoded storage line for the provided task.
     *
     * @param task Task to encode.
     * @return Encoded task line.
     * @throws TomException If the task type is unsupported.
     */
    public static String encode(Task task) throws TomException {
        if (task instanceof Todo) {
            return "T | " + (task.isDone ? "1" : "0") + " | " + task.description;
        } else if (task instanceof Deadline) {
            Deadline deadline = (Deadline) task;
            return "D | " + (task.isDone ? "1" : "0") + " | " + task.description + " | " + deadline.by.toString();
        } else if (task instanceof Event) {
            Event event = (Event) task;
            return "E | " + (task.isDone ? "1" : "0") + " | " + task.description + " | " + event.from + " | " + event.to.toString(); 
        } else {
            throw new TomException("Unknown Task Type");
        }
    }

    /**
     * Returns the decoded task represented by the provided storage line.
     *
     * @param line Storage line to decode.
     * @return The decoded task.
     * @throws TomException If the line cannot be decoded.
     */
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

    /**
     * Writes the provided tasks to storage.
     *
     * @param tasks Tasks to persist.
     */
    public static void updateStorage(List<Task> tasks) {
        try {
            Path filePath = getStoragePath();
            List<String> lines = new java.util.ArrayList<>();
            for (Task task : tasks) {
                lines.add(encode(task));
            }
            Files.write(filePath, lines);
            // System.out.println("Storage updated successfully.");
        } catch (java.io.IOException e) {
            System.out.println("Error updating storage: " + e.getMessage());
        } catch (TomException e) {
            System.out.println("Error encoding tasks: " + e.getMessage());
        }
    }

    /**
     * Returns the tasks loaded from storage.
     *
     * @return List of tasks loaded from storage.
     * @throws TomException If decoding fails.
     */
    public  List<Task> load() throws TomException {
        List<Task> tasks = new java.util.ArrayList<>();
        try {
            ensureStorageExists();
            Path filePath = FILE_PATH;
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
            
        } catch (java.io.IOException e) {
            System.out.println("Error loading storage: " + e.getMessage());
        }
        return tasks;
    }

    /**
     * Runs a simple storage path lookup for debugging.
     *
     * @param args Command-line arguments (unused).
     */
    public static void main(String[] args) {
        getStoragePath();
        getStoragePath();
    }
}
