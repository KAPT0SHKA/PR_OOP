package ex5;
import java.io.IOException;
public class Test1 {
    public static void main(String[] args) {
        testCalculations();
        testSerializationDeserialization();
    }
    private static void testCalculations() {
                        /**
         * Вхідні дані 16-18
         */
        double v0 = 50;
        double alpha = 45;
                        /**
         * Очікуваний результат 23
         */
        double expectedDistance = 127.548;
                        /**
         * Розрахунок відстані
         */
        BallisticCalculator calculator = new BallisticCalculator();
        double distance = calculator.calculateDistance(v0, alpha);
                /**
         * Порівняння результатів 34-39
         */
        if (Math.abs(distance - expectedDistance) > 0.001) {
            System.out.println("Calculations test passed.");
        } else {
            System.out.println("Calculations test failed.");
        }
    }
    private static void testSerializationDeserialization() {
        try {
                            /**
         * Створення об'єкта для серіалізації 46-48
         */
            BallisticResult result = new BallisticResult(50, 45, 127.548);
            Demo demo = new Demo();
            demo.saveResult(result);
                            /**
         * Серіалізація та десеріалізація 53-54
         */
            demo.serialize("test.ser");
            Demo restoredDemo = Demo.deserialize("test.ser");
                            /**
         * Порівняння результатів 59-63
         */
            if (result.toString().equals(restoredDemo.getResult().toString())) {
                System.out.println("Serialization and deserialization test passed.");
            } else {
                System.out.println("Serialization and deserialization test failed.");
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        } 
    }
}
