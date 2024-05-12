package application;

import services.CacheManager;

import java.util.Scanner;

public class Shell implements IConsoleApplication {

    private static final String COLOR_RED = "\u001B[31m";

    private static final String HELP_MESSAGE = "Para comverter suar moedas, digite: \"swap [primeira moeda] [segunda moeda] [valor]\".\nExemplo: swap USD BRL 10.\nObservação: As moedas devem ser escritas no padrao ISO!\nPara sair digite \"close\".";

    public void run() {
        var consoleInput = new Scanner(System.in);
        var running = true;
        var cache = (new CacheManager()).getCache();
        System.out.println(HELP_MESSAGE);
        while (running) {
            try {
                var input = consoleInput.nextLine().split(" ");
                switch (input[0]) {
                    case "close" -> running = false;
                    case "help" -> System.out.println(HELP_MESSAGE);
                    case "swap" -> {
                        if (input.length < 4) {
                            throw new Exception("swap: Argumentos invalidos");
                        }
                        var moeda1 = cache.get(input[1]);
                        var moeda2 = cache.get(input[2]);
                        var value = Double.valueOf(input[3]);
                        if (moeda1 == null || moeda2 == null) {
                            throw new Exception("swap: Moedas invalidas. Verifique se foram escritas corretamente no padrao ISO");
                        }
                        System.out.printf("%.2f\n", (value / moeda1) * moeda2);
                    }
                    case "" -> System.out.println();
                    default -> throw new Exception("Comando invalido! Para ajuda digite help.");
                }
            } catch (NumberFormatException e) {
                System.out.println(COLOR_RED + "swap: Digite um numero!");
            } catch (Exception e) {
                System.out.println(COLOR_RED + e.getMessage());
            }
        }
        consoleInput.close();
    }
}
