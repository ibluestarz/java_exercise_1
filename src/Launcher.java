
import java.util.Scanner;

public class Launcher {
    public static void main(String[] args) {
        System.out.println("Bienvenue!");
        Scanner scanner = new Scanner(System.in);
        String input;

        while (true) {
            input = scanner.nextLine();
            if (input.equals("quit")) {
                break;
            } else {
                System.out.println("Unknown command");
            }
        }

        System.out.println("Au revoir !");
    }
}
