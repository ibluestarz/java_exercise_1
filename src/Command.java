// Imports
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.Arrays;
import java.util.Collections;
import java.util.Map;
import java.util.Scanner;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

// COmmand
interface Command {
    String name();
    boolean run(Scanner scanner);
}

class Quit implements Command {
    @Override
    public String name() {
        return "quit";
    }

    @Override
    public boolean run(Scanner scanner) {
        return true;
    }
}

class Fibo implements Command {
    @Override
    public String name() {
        return "fibo";
    }

    private static int fib(int n) {
        if (n <= 1) return n;
        else return fib(n-1) + fib(n-2);
    }

    @Override
    public boolean run(Scanner scanner) {
        System.out.println("Veuillez saisir un indice:");
        int n = scanner.nextInt();
        scanner.nextLine();
        System.out.println(fib(n));
        return false;
    }
}

class Freq implements Command {
    @Override
    public String name() {
        return "freq";
    }

    @Override
    public boolean run(Scanner scanner) {
        System.out.println("Veuillez saisir le chemin du fichier à analyser:");
        Path filePath = Paths.get(scanner.nextLine());
        try {
            String[] words = Files.readString(filePath).toLowerCase().split(" ");
            Map<String, Long> counter = Arrays.stream(words).collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
            counter.entrySet().stream()
                    .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
                    .limit(3).forEach((obj) -> System.out.print(obj.getKey() + " "));
            System.out.println();
        } catch (IOException e) {
            System.out.println("Unreadable file: " + e.getClass().getSimpleName() + " - " + e.getMessage());
        }
        return false;
    }
}

class Predict implements Command {

    @Override
    public String name() {
        return "predict";
    }

    public static HashMap<String, Integer> frequence(String str, String word) {
        var map = new HashMap<String, Integer>();
        String[] text = str.split("[ \n]");
        ArrayList<String> tab = new ArrayList<>(Arrays.asList(text));
        int i = tab.indexOf(word);
        while (i < tab.size() && i != -1)
        {
            tab.set(i, "");
            String nextWord = tab.get(i + 1);
            map.merge(nextWord, 1, Integer::sum);
            i = tab.indexOf(word);
        }
        return map;
    }

    @Override
    public boolean run(Scanner scanner) {
        System.out.println("Veuillez saisir le chemin du fichier à analyser:");
        Path filePath = Paths.get(scanner.nextLine());
        try {
            String text = Files.readString(filePath).toLowerCase();
            System.out.println("Veuillez saisir le premier mot:");
            String word = scanner.nextLine().toLowerCase();
            String result = "";
            for (int i = 0; i < 20; i++) {
                try {
                    result += word + " ";
                    var map = frequence(text, word);
                    word = Collections.max(map.entrySet(), Map.Entry.comparingByValue()).getKey();
                } catch (Exception e) {
                    System.out.println("Le mot ne figure pas dans le texte.");
                    return false;
                }
            }
            System.out.println(result);
        } catch (IOException e) {
            System.out.println("Unreadable file: " + e.getClass().getSimpleName() + " - " + e.getMessage());
        }
        return false;
    }

}
