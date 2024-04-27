import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        var consoleInput = new Scanner(System.in);
        var runing = true;
        var cacheUpdater = new Updater();
        Map<String, Double> map = null;
        while (runing) {
            try {
                var input = consoleInput.nextLine().split(" ");
                switch (input[0]) {
                    case "close" -> runing = false;
                    case "help" -> System.out.println("help mensage");
                    case "swap" -> {
                        if (map == null) {
                            map = cacheUpdater.getMap();
                        }
                        var value1 = map.get(input[1]);
                        var value2 = map.get(input[2]);
                        var value3 = Double.valueOf(input[3]);
                        System.out.printf("%.2f\n", (value3 / value1) * value2);
                    }
                }
            } catch (Throwable e) {
                System.out.println(e.getMessage());
            }
        }
    }
}