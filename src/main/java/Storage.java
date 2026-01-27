import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
public class Storage {
    
    public static final Path FILE_PATH = Paths.get("data", "tasks.txt");

    public static void ensureStorageExists() throws IOException {
        Files.createDirectories(FILE_PATH.getParent());
        if (!Files.exists(FILE_PATH)) {
            Files.createFile(FILE_PATH);
        }
    }

    public static Path getStoragePath() {
        String userDir = System.getProperty("user.dir");
        Path path = Paths.get(userDir, "data", "tasks.txt");
        // System.out.println("Storage path: " + path);
        return path;
    }

    public static void createStorageDirectory() {
        try {
            Path storagePath = getStoragePath().getParent();
            java.nio.file.Files.createDirectories(storagePath);
            // System.out.println("Storage directory created at: " + storagePath);
        } catch (java.io.IOException e) {
            System.out.println("Error creating storage directory: " + e.getMessage());
        }
    }

    public static void createEmptytxt() { // creates empty tasks.txt if it doesn't exist
        try {
            Path filePath = FILE_PATH;
            if (!java.nio.file.Files.exists(filePath)) {
                ensureStorageExists();
                java.nio.file.Files.createFile(filePath);
                // System.out.println("Created empty tasks.txt at: " + filePath);
            } else {
                // System.out.println("tasks.txt already exists at: " + filePath);
            }
        } catch (java.io.IOException e) {
            System.out.println("Error creating tasks.txt: " + e.getMessage());
        }
    }

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

    public static List<Task> load() throws TomException {
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

    public static void main(String[] args) {
        getStoragePath();
        createEmptytxt();
        getStoragePath();
    }
}
