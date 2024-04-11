package ex7;

import java.io.*;
import java.util.*;

public class Main {
    private static final int MAX_RESULTS_TO_KEEP = 5; // Кількість останніх результатів, які потрібно зберегти
    private static final String RESULT_FILE = "result.ser"; // Ім'я файлу для збереження результатів

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        char input;

        /** Черга для зберігання результатів 15*/
        Queue<BallisticResult> resultsQueue = new LinkedList<>();

        /** Завантаження попередніх результатів, якщо вони є 18*/
        loadPreviousResults(resultsQueue);

        do {
            /** Питання користувача про виведення таблиці, введення даних або вихід з програми 21-58*/
            System.out.println("Натисніть 'т' для виведення таблиці:");
            System.out.println("Натисніть 'і' для введення даних:");
            System.out.println("Натисніть 'м' для виходу з програми:");
            input = scanner.next().charAt(0);
            if (input == 'т') {
                displayTable(resultsQueue);
            } else if (input == 'м') {
                break; /** Вийти з програми */
            } else if (input == 'і') {
                /** Введення даних 32-33*/
                System.out.println("Введіть початкову швидкість (m/s): ");
                double v0 = scanner.nextDouble();

                System.out.println("Введіть кут запуску : ");
                double alpha = scanner.nextDouble();

                /** Обчислення відстані 39-41*/
                BallisticCalculator calculator = new BallisticCalculator();
                double distance = calculator.calculateDistance(v0, alpha);
                System.out.println("Дистанція: " + distance + " метрів");

                /** Збереження результатів 44-45*/
                BallisticResult result = new BallisticResult(v0, alpha, distance);
                resultsQueue.offer(result); // Додаємо новий результат до черги

                /** Якщо черга перевищує максимальний розмір, видаляємо найстаріший результат 48-50*/
                while (resultsQueue.size() > MAX_RESULTS_TO_KEEP) {
                    resultsQueue.poll();
                }

                /** Зберігаємо всю чергу у файл 53-54*/
                saveResultsToFile(resultsQueue);
            }
        } while (true);

        scanner.close();
    }

    private static void loadPreviousResults(Queue<BallisticResult> resultsQueue) {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(RESULT_FILE))) {
            while (true) {
                BallisticResult result = (BallisticResult) in.readObject();
                resultsQueue.offer(result);
            }
        } catch (EOFException ignored) {
            // Кінець файлу
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static void saveResultsToFile(Queue<BallisticResult> resultsQueue) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(RESULT_FILE))) {
            // Записуємо всі результати у файл
            for (BallisticResult r : resultsQueue) {
                out.writeObject(r);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void displayTable(Queue<BallisticResult> resultsQueue) {
        /** Виведення у вигляді таблиці 86-105*/
        List<String> headers = new ArrayList<>();
        headers.add("Початкова швидкість (m/s)");
        headers.add("Кут запуску");
        headers.add("Відстань (м)");

        List<List<String>> data = new ArrayList<>();
        for (BallisticResult result : resultsQueue) {
            List<String> row = new ArrayList<>();
            row.add(String.valueOf(result.v0)); // Отримання значення поля напряму
            row.add(String.valueOf(result.alpha)); // Отримання значення поля напряму
            row.add(String.valueOf(result.distance)); // Отримання значення поля напряму
            data.add(row);
        }

        ViewTable viewTable = new ViewTable(headers, data);
        viewTable.displayTable();

        //** Виведення мінімального та максимального значення "Відстань (м)" 104*/
        displayMinMaxDistance(resultsQueue);
    }

    private static void displayMinMaxDistance(Queue<BallisticResult> resultsQueue) {
        /** Пошук мінімального та максимального значення "Відстань (м)" 107-122*/
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
