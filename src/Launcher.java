import java.util.*;
public class Launcher {

    public static void main(String[] args) {
        System.out.println("Bienvenue dans mon Launcher, étranger.  Commande : quit, fibo, freq, predict.");

        List<Command> commands = new ArrayList<>();
        commands.add(new Quit());
        commands.add(new Fibo());
        commands.add(new Freq());
        commands.add(new Predict());

        Scanner scan = new Scanner(System.in);

        boolean running = true;
        while (running) {
            String input = scan.nextLine();
            boolean known = false;
            for(Command command: commands) {
                if (command.name().equals(input)) {
                    known = true;
                    running = !command.run(scan);
                    break;
                }
            }
            if (!known) {
                System.out.println("Unknown command");
            }
        }
        scan.close();
    }
}
