package application;

import services.Updater;

import java.util.Map;
import java.util.Scanner;

public class Menu implements IConsoleApplication {
    public void run() {
        var consoleInput = new Scanner(System.in);
        var cacheUpdater = new Updater();
        Map<String, Double> cache = cacheUpdater.getCache();
        var choice = consoleInput.nextInt();
        System.out.println();
        switch (choice) {
            case 1 -> {
                var moeda1 = cache.get("BRL");
                var moeda2 = cache.get("USD");
                var valor = consoleInput.nextDouble();
                var resultado = (valor / moeda1) * moeda2;
                System.out.printf("%2.2f Reais valem %2.2f Dolares\n",valor,resultado);
            }
            case 2 -> {
                var moeda1 = cache.get("BRL");
                var moeda2 = cache.get("BOB");
                var valor = consoleInput.nextDouble();
                var resultado = (valor / moeda1) * moeda2;
                System.out.printf("%2.2f Reais valem %2.2f Pesos Bolivianos\n",valor,resultado);
            }
            case 3 -> {
                var moeda1 = cache.get("BRL");
                var moeda2 = cache.get("ARS");
                var valor = consoleInput.nextDouble();
                var resultado = (valor / moeda1) * moeda2;
                System.out.printf("%2.2f Reais valem %2.2f Pesos Argentinos\n",valor,resultado);
            }
            case 4 -> {
                var moeda1 = cache.get("USD");
                var moeda2 = cache.get("BRL");
                var valor = consoleInput.nextDouble();
                var resultado = (valor / moeda1) * moeda2;
                System.out.printf("%2.2f Reais valem %2.2f Reais\n",valor,resultado);
            }
            case 5 -> {
                var moeda1 = cache.get("BOB");
                var moeda2 = cache.get("BRL");
                var valor = consoleInput.nextDouble();
                var resultado = (valor / moeda1) * moeda2;
                System.out.printf("%2.2f Pesos Bolivianos valem %2.2f Reais\n",valor,resultado);
            }
            case 6 -> {
                var moeda1 = cache.get("ARS");
                var moeda2 = cache.get("BRL");
                var valor = consoleInput.nextDouble();
                var resultado = (valor / moeda1) * moeda2;
                System.out.printf("%2.2f Pesos Argentinos valem %2.2f Reais\n",valor,resultado);
            }
        }
        consoleInput.close();
    }
}
