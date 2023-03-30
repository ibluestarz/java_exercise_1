import java.util.Scanner;

public class Launcher {
    public static void main(String[] args) {
        System.out.println("Bienvenue!");
        Scanner scanner = new Scanner(System.in);
        String input = "";

        while (!input.equals("quit")) { // cond while
            System.out.print("Entrez une commande : ");
            input = scanner.nextLine();

            if (input.equals("quit")) {
                System.out.println("Au revoir !");
            } else if (input.equals("fibo")) {
                System.out.print("Entrez un nombre : ");
                int n = scanner.nextInt();
                scanner.nextLine(); // au cas ou il y a un vide
                int fibo = fibonacci(n); //fibo 
                System.out.println("La valeur de la suite de Fibonacci de " + n + " est : " + fibo);
            } else {
                System.out.println("Commande inconnue");
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
