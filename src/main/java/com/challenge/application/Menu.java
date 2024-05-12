package com.challenge.application;

import com.challenge.services.Swapper;

import java.util.Scanner;

public class Menu implements IConsoleApplication {
    private static final String WELCOME_MESSAGE = """
            Seja bem-vindo/a ao Conversor de Moeda =]
            1) Real brasileiro =>> Dólar
            2) Real brasileiro =>> Peso boliviano
            3) Real brasileiro =>> Peso argentino
            4) Dólar =>> Real brasileiro
            5) Peso boliviano =>> Real brasileiro
            6) Peso argentino =>> Real brasileiro
            7) Modo Shell
            8) Sair
            Escolha uma opção válida:""";

    public void run() {
        var consoleInput = new Scanner(System.in);
        var swapper = new Swapper();
        while (true) {
            System.out.println(WELCOME_MESSAGE);
            var choice = consoleInput.nextInt();
            if (choice > 0 && choice < 7) {
                System.out.println("Digite o valor que deseja comverter:");
                if (consoleInput.hasNextDouble()) {
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
                    }
                    System.out.println();
                } else {
                    System.out.println("Digite um numero!");
                }
            } else if (choice == 7) {
                (new Shell()).run();
                return;
            } else if (choice == 8) {
                break;
            } else {
                System.out.println("Digite uma opção valida!");
            }
        }
        consoleInput.close();
    }
}