package application;

import services.Swapper;
import java.util.Scanner;

public class Menu implements IConsoleApplication {
    public void run() {
        var consoleInput = new Scanner(System.in);
        var swapper = new Swapper();
        var choice = consoleInput.nextInt();
        if (choice == 7 ) {
            new Shell().run();
            return;
        }
        System.out.println();
        var valor = consoleInput.nextDouble();
        switch (choice) {
            case 1 -> {
                var resultado = swapper.swap("BRL", "USD", valor);
                System.out.printf("%2.2f Reais valem %2.2f Dolares\n", valor, resultado);
            }
            case 2 -> {
                var resultado = swapper.swap("BRL", "BOB", valor);
                System.out.printf("%2.2f Reais valem %2.2f Pesos Bolivianos\n", valor, resultado);
            }
            case 3 -> {
                var resultado = swapper.swap("BRL", "ARS", valor);
                System.out.printf("%2.2f Reais valem %2.2f Pesos Argentinos\n", valor, resultado);
            }
            case 4 -> {
                var resultado = swapper.swap("USD", "BRL", valor);
                System.out.printf("%2.2f Reais valem %2.2f Reais\n", valor, resultado);
            }
            case 5 -> {
                var resultado = swapper.swap("BOB", "BRL", valor);
                System.out.printf("%2.2f Pesos Bolivianos valem %2.2f Reais\n", valor, resultado);
            }
            case 6 -> {
                var resultado = swapper.swap("ARS", "BRL", valor);
                System.out.printf("%2.2f Pesos Argentinos valem %2.2f Reais\n", valor, resultado);
            }
            default -> System.out.println("Digite uma opção valida!");
        }
        consoleInput.close();
    }
}