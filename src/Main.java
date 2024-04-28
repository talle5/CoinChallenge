import java.util.Map;
import java.util.Scanner;

public class Main {

    public static final String RED = "\u001B[31m";

    public static void main(String[] args) {
        var consoleInput = new Scanner(System.in);
        var running = true;
        var cacheUpdater = new Updater();
        Map<String, Double> cache = cacheUpdater.getCache();
        while (running) {
            try {
                var input = consoleInput.nextLine().split(" ");
                switch (input[0]) {
                    case "close" -> running = false;
                    case "help" -> System.out.println("help mensage");
                    case "swap" -> {
                        if (input.length < 4) {
                            throw new RuntimeException("swap: Argumentos invalidos");
                        }
                        var value1 = cache.get(input[1]);
                        var value2 = cache.get(input[2]);
                        var value3 = Double.valueOf(input[3]);
                        if (value1 == null || value2 == null) {
                            throw new RuntimeException("swap: Moedas invalidas. Verifique se foram escritas corretamente no padrao ISO");
                        }
                        System.out.printf("%.2f\n", (value3 / value1) * value2);
                    }
                    case "" -> System.out.println();
                    default -> throw new RuntimeException("Comando invalido! Para ajuda digite help.");
                }
            } catch (Exception e) {
                System.out.println(RED + e.getMessage());
            }
        }
    }
}