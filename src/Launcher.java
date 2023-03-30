import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Launcher {
    public static void main(String[] args) {
        System.out.println("Bienvenue!");
        Scanner scanner = new Scanner(System.in);
        String input = "";

        while (!input.equals("quit")) {
            System.out.print("Entrez une commande : ");
            input = scanner.nextLine();

            if (input.equals("quit")) {
                System.out.println("Au revoir !");
            } else if (input.equals("fibo")) {
                System.out.print("Entrez un nombre : ");
                int n = scanner.nextInt();
                scanner.nextLine();
                int fibo = fibonacci(n);
                System.out.println("La valeur de la suite de Fibonacci de " + n + " est : " + fibo);
            } else if (input.equals("freq")) {
                System.out.print("Entrez le chemin du fichier : ");
                String pathString = scanner.nextLine();
                Path path = Paths.get(pathString);

                try {
                    String content = Files.readString(path);
                    String[] words = content.replaceAll("[^a-zA-Z ]", " ").toLowerCase().split("\\s+");

                    Map<String, Long> wordFrequencies = Arrays.stream(words)
                            .filter(w -> !w.isBlank())
                            .collect(Collectors.groupingBy(w -> w, Collectors.counting()));

                    String mostFrequentWords = wordFrequencies.entrySet().stream()
                            .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                            .limit(3)
                            .map(Map.Entry::getKey)
                            .collect(Collectors.joining(" "));

                    System.out.println("Les mots les plus fréquents sont : " + mostFrequentWords);

                } catch (IOException e) {
                    System.out.println("Unreadable file: " + e.getClass().getSimpleName() + " - " + e.getMessage());
                }
            } else {
                System.out.println("Unknown command");
            }
        }
    }

    public static int fibonacci(int n) {
        if (n == 0 || n == 1) {
            return n;
        }
        return fibonacci(n - 1) + fibonacci(n - 2);
    }
}
