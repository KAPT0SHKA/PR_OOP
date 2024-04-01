package src.Завдання2.Test;


import java.io.IOException;

public class Test {
    public static void main(String[] args) {
        testCalculations();
        testSerializationDeserialization();
    }

    // Метод для тестування обчислень
    private static void testCalculations() {
        // Вхідні дані
        double v0 = 50; // Початкова швидкість у м/с
        double alpha = 45; // Кут у градусах

        // Очікуваний результат
        double expectedDistance = 127.548;

        // Обчислення дистанції
        BallisticCalculator calculator = new BallisticCalculator();
        double distance = calculator.calculateDistance(v0, alpha);

        // Порівняння результатів
        if (Math.abs(distance - expectedDistance) < 0.001) {
            System.out.println("Calculations test passed.");
        } else {
            System.out.println("Calculations test failed.");
        }
    }

    // Метод для тестування серіалізації та десеріалізації
    private static void testSerializationDeserialization() {
        try {
            // Створення об'єкту для серіалізації
            BallisticResult result = new BallisticResult(50, 45, 127.548);
            Demo demo = new Demo();
            demo.saveResult(result);

            // Серіалізація та десеріалізація
            demo.serialize("test.ser");
            Demo restoredDemo = Demo.deserialize("test.ser");

            // Порівняння результатів
            if (result.toString().equals(restoredDemo.result.toString())) {
                System.out.println("Serialization and deserialization test passed.");
            } else {
                System.out.println("Serialization and deserialization test failed.");
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
