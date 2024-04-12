// Файл Main.java
package ex4;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Введення даних
        System.out.println("Введіть початкову швидкість (m/s): ");
        double v0 = scanner.nextDouble();

        System.out.println("Введіть кут запуску : ");
        double alpha = scanner.nextDouble();

        // Обчислення відстані
        BallisticCalculator calculator = new BallisticCalculator();
        double distance = calculator.calculateDistance(v0, alpha);
        System.out.println("Дистанція: " + distance + " метрів");

        // Збереження результатів
        BallisticResult result = new BallisticResult(v0, alpha, distance);
        try {
            saveResultToFile(result);
            System.out.println("Результати збережено у файл.");
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Питання користувача про виведення таблиці
        System.out.println("Натисніть 'т' для виведення таблиці:");
        char input = scanner.next().charAt(0);
        if (input == 'т') {
            // Виведення у вигляді таблиці
            displayTable(v0, alpha, distance);
        } else {
            System.out.println("До побачення!");
        }

        scanner.close();
    }

    private static void saveResultToFile(BallisticResult result) throws IOException {
        try (FileOutputStream fileOut = new FileOutputStream("result.ser");
             ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
            out.writeObject(result);
        }
    }

    private static void displayTable(double v0, double alpha, double distance) {
        // Виведення у вигляді таблиці
        List<String> headers = new ArrayList<>();
        headers.add("Початкова швидкість (m/s)");
        headers.add("Кут запуску");
        headers.add("Відстань (м)");

        List<List<String>> data = new ArrayList<>();
        List<String> row = new ArrayList<>();
        row.add(String.valueOf(v0));
        row.add(String.valueOf(alpha));
        row.add(String.valueOf(distance));
        data.add(row);

        ViewTable viewTable = new ViewTable(headers, data);
        viewTable.displayTable();
    }
}
