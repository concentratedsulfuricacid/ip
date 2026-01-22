import java.util.Scanner;
public class Tom {
    static Scanner scanner = new Scanner(System.in);
    static String border = "____________________________________________________________";
    
    public static void echo(String input){
        System.out.println(border);
        System.out.println(input);
        System.out.println(border);
    }
    static String[] items = new String[100];

    public static void add(String items) {
        for (int i = 0; i < Tom.items.length; i++) {
            if (Tom.items[i] == null) {
                Tom.items[i] = items;

                System.out.println(border);
                System.out.println("added: " + items);
                System.out.println(border);
                break;
            }
        }
    }

    public static void list_items() {
        System.out.println(border);
        for (int i = 0; i < items.length; i++) {
            if (items[i] != null) {
                System.out.println((i + 1) + ". " + items[i]);
            }
        }
        System.out.println(border);
    } 
    
    public static void main(String[] args) {
        String greeting = "Hello! I'm Tom! \n"
                        + "What can I do for you?";
        String exit = "Bye. Hope to see you again soon!";

        
        System.out.println(border);
        System.out.println(greeting);
        System.out.println(border);

        String message = scanner.nextLine();
        while (!message.equals("bye")) {
            if (message.equals("list")) {
                list_items();
            } else {
                add(message);
            }
            message = scanner.nextLine();
        }
        
        System.out.println(border);
        System.out.println(exit);
        System.out.println(border);

    }
}
