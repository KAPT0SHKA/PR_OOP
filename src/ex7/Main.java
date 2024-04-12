package ex7;

import java.io.*;
import java.util.*;

public class Main {
    private static final int MAX_RESULTS_TO_KEEP = 5; // Кількість останніх результатів, які потрібно зберегти
    private static final String RESULT_FILE = "result.ser"; // Ім'я файлу для збереження результатів

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        char input;

        Queue<BallisticResult> resultsQueue = new LinkedList<>();

        loadPreviousResults(resultsQueue);

        do {
            System.out.println("Натисніть 'т' для виведення таблиці:");
            System.out.println("Натисніть 'і' для введення даних:");
            System.out.println("Натисніть 'м' для виходу з програми:");
            input = scanner.next().charAt(0);
            if (input == 'т') {
                displayTable(resultsQueue);
            } else if (input == 'м') {
                break;
            } else if (input == 'і') {
                System.out.println("Введіть початкову швидкість (m/s): ");
                double v0 = scanner.nextDouble();

                System.out.println("Введіть кут запуску : ");
                double alpha = scanner.nextDouble();

                BallisticCalculator calculator = new BallisticCalculator();
                double distance = calculator.calculateDistance(v0, alpha);
                System.out.println("Дистанція: " + distance + " метрів");

                BallisticResult result = new BallisticResult(v0, alpha, distance);
                resultsQueue.offer(result);

                while (resultsQueue.size() > MAX_RESULTS_TO_KEEP) {
                    resultsQueue.poll();
                }

                saveResultsToFile(resultsQueue);
            }
        } while (true);

        scanner.close();
    }

    private static void loadPreviousResults(Queue<BallisticResult> resultsQueue) {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(RESULT_FILE))) {
            while (true) {
                try {
                    BallisticResult result = (BallisticResult) in.readObject();
                    resultsQueue.offer(result);
                } catch (EOFException ignored) {
                    // End of file reached
                    break;
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            // Log the exception instead of printing stack trace
            System.err.println("Error loading previous results: " + e.getMessage());
        }
    }


    private static void saveResultsToFile(Queue<BallisticResult> resultsQueue) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(RESULT_FILE))) {
            for (BallisticResult r : resultsQueue) {
                out.writeObject(r);
            }
        } catch (IOException e) {
            // Log the exception instead of printing stack trace
            System.err.println("Error saving results to file: " + e.getMessage());
        }
    }

    private static void displayTable(Queue<BallisticResult> resultsQueue) {
        List<String> headers = new ArrayList<>();
        headers.add("Початкова швидкість (m/s)");
        headers.add("Кут запуску");
        headers.add("Відстань (м)");

        List<List<String>> data = new ArrayList<>();
        for (BallisticResult result : resultsQueue) {
            List<String> row = new ArrayList<>();
            row.add(String.valueOf(result.v0));
            row.add(String.valueOf(result.alpha));
            row.add(String.valueOf(result.distance));
            data.add(row);
        }

        ViewTable viewTable = new ViewTable(headers, data);
        viewTable.displayTable();

        displayMinMaxDistance(resultsQueue);
    }

    private static void displayMinMaxDistance(Queue<BallisticResult> resultsQueue) {
        double minDistance = Double.MAX_VALUE;
        double maxDistance = Double.MIN_VALUE;
        for (BallisticResult result : resultsQueue) {
            if (result.distance < minDistance) {
                minDistance = result.distance;
            }
            if (result.distance > maxDistance) {
                maxDistance = result.distance;
            }
        }

        System.out.println("Мінімальна дистанція: " + minDistance + " метрів");
        System.out.println("Максимальна дистанція: " + maxDistance + " метрів");
    }
}
