package src.Завдання2;

import java.io.*;
import java.util.Scanner;

public class BallisticsCalculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Введення даних
        System.out.println("Введіть початкову швидкість (m/s): ");
        double v0 = scanner.nextDouble();

        System.out.println("Введіть кут запуску : ");
        double alpha = scanner.nextDouble();

        // Тестування обчислення відстані
        BallisticCalculator calculator = new BallisticCalculator();
        double distance = calculator.calculateDistance(v0, alpha);
        System.out.println("Дистанція: " + distance + " метрів");

        // Збереження результатів
        BallisticResult result = new BallisticResult(v0, alpha, distance);
        Demo demo = new Demo();
        demo.saveResult(result);

        // Збереження та відновлення стану об'єкта
        try {
            demo.serialize("demo.ser"); // Серіалізація
            Demo restoredDemo = Demo.deserialize("demo.ser"); // Десеріалізація
            restoredDemo.displayResult();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        
        scanner.close();
    }
}
