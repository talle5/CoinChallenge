package application;

import services.Updater;

import java.util.Map;
import java.util.Scanner;

public class Shell implements IConsoleApplication {

    private static final String RED = "\u001B[31m";

    private static final String HELP = """
            Bem-Vimdo ao comvesor de moedas!
            Para comverter suar moedas, digite: "swap [primeira moeda] [segunda moeda] [valor]".
            Exemplo: swap USD BRL 10.
                       \s
            Observaçõa: As moedas devem ser escritas no padrao ISO!
                       \s
            Para sair digite "close".
           \s""";

    public void run() {
        var consoleInput = new Scanner(System.in);
        var running = true;
        var cacheUpdater = new Updater();
        Map<String, Double> cache = cacheUpdater.getCache();
        System.out.println(HELP);
        while (running) {
            try {
                var input = consoleInput.nextLine().split(" ");
                switch (input[0]) {
                    case "close" -> running = false;
                    case "help" -> System.out.println(HELP);
                    case "swap" -> {
                        if (input.length < 4) {
                            throw new RuntimeException("swap: Argumentos invalidos");
                        }
                        var moeda1 = cache.get(input[1]);
                        var moeda2 = cache.get(input[2]);
                        var value = Double.valueOf(input[3]);
                        if (moeda1 == null || moeda2 == null) {
                            throw new RuntimeException("swap: Moedas invalidas. Verifique se foram escritas corretamente no padrao ISO");
                        }
                        System.out.printf("%.2f\n", (value / moeda1) * moeda2);
                    }
                    case "" -> System.out.println();
                    default -> throw new RuntimeException("Comando invalido! Para ajuda digite help.");
                }
            } catch (Exception e) {
                System.out.println(RED + e.getMessage());
            }
        }
        consoleInput.close();
    }
}
