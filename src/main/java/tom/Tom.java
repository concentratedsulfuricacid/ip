package tom;

import java.util.ArrayList;
import java.util.Scanner;

import tom.command.Command;
import tom.storage.Storage;
import tom.task.TaskList;
import tom.ui.Ui;

public class Tom {
    private final Storage storage;
    private final Ui ui;
    private final TaskList items;
    private static final Scanner SCANNER = new Scanner(System.in);

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

    public void run() {
        ui.showWelcomeMessage();
        boolean isExit = false;
        while (!isExit) {
            try {
                String message = SCANNER.nextLine().trim();
                Command c = Parser.parse(message);
                c.execute(items, ui, storage);
                isExit = c.isExit();
            } catch (TomException e) {
                System.out.println(e.getMessage());
            } finally {
                ui.border();
            }
        }
    }

    public static void main(String[] args) {
        Tom tom = new Tom("data/tasks.txt");
        tom.run();
    }
}
