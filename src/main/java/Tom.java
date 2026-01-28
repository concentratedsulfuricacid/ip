import java.util.Scanner;
import java.util.ArrayList;

public class Tom {
    private Storage storage;
    private Ui ui;
    private TaskList items;
    static Scanner scanner = new Scanner(System.in);


    public Tom(String Filepath) {
        this.storage = new Storage(Filepath);
        this.ui = new Ui();
        try {
            items = new TaskList(storage.load());
        } catch (TomException e) {
            ui.showLoadingError();
            items = new TaskList(new ArrayList<>());
        }
    }

    public void run() {
        ui.showWelcomeMessage();
        boolean isExit = false;
        while(!isExit){
            try {
                String message = scanner.nextLine().trim();
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
