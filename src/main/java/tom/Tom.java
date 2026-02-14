package tom;

import java.util.ArrayList;
import java.util.Scanner;

import tom.command.Command;
import tom.storage.Storage;
import tom.task.TaskList;
import tom.ui.Ui;

/**
 * Provides the main application entry point and runtime loop.
 */
public class Tom {
    private final Storage storage;
    private final Ui ui;
    private final TaskList items;
    /** Shared scanner for reading user input. */
    private static final Scanner SCANNER = new Scanner(System.in);

    /**
     * Creates a Tom instance that persists tasks to the provided file path.
     *
     * @param filePath File path to the storage file.
     */
    public Tom(String filePath) {
        this.storage = new Storage(filePath);
        this.ui = new Ui();
        TaskList loadedItems;
        try {
            loadedItems = new TaskList(storage.load());
        } catch (TomException e) {
            ui.showLoadingError();
            loadedItems = new TaskList(new ArrayList<>());
        }
        this.items = loadedItems;
    }

    /**
     * Runs the command processing loop until an exit command is received.
     */
    public void run() {
        System.out.println(ui.showWelcomeMessage());
        boolean isExit = false;
        while (!isExit) {
            try {
                String message = SCANNER.nextLine().trim();
                Command c = Parser.parse(message);
                String response = c.execute(items, ui, storage);
                System.out.println(response);
                isExit = c.isExit();
            } catch (TomException e) {
                System.out.println(e.getMessage());
            } finally {
                String border = ui.border();
                if (!border.isEmpty()) {
                    System.out.println(border);
                }
            }
        }
    }

    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            return c.execute(items, ui, storage);
        } catch (TomException e) {
            return e.getMessage();
        }

    }

    public String getWelcomeMessage() {
        return ui.showWelcomeMessage();
    }

    /**
     * Starts the application with a default storage path.
     *
     * @param args Command-line arguments (unused).
     */
    public static void main(String[] args) {
        Tom tom = new Tom("data/tasks.txt");
        tom.run();
    }

    
    
    
}
