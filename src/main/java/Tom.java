import java.util.Scanner;
public class Tom {
    static Scanner scanner = new Scanner(System.in);
    static String border = "____________________________________________________________";
    
    public static void echo(String input){
        System.out.println(border);
        System.out.println(input);
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
            echo(message);
            message = scanner.nextLine();
        }
        
        System.out.println(border);
        System.out.println(exit);
        System.out.println(border);

    }
}
