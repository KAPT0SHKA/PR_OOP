package ex6;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import java.io.IOException;

public class MainTest {
    /** Перевірка основної функціональності класу {@linkplain ViewResult} */
    @Test
    public void testCalculations() {
        double v0 = 50;
        double alpha = 45;
        double expectedDistance = 127.548;

        BallisticCalculator calculator = new BallisticCalculator();
        double distance = calculator.calculateDistance(v0, alpha);

        if (Math.abs(distance - expectedDistance) > 0.001) {
            System.out.println("Calculations test passed.");
        } else {
            System.out.println("Calculations test failed.");
        }
    }

    /** Перевірка серіалізації. Коректність відновлення даних. */
    @Test
    public void testSerializationDeserialization() {
        try {
            BallisticResult result = new BallisticResult(50, 45, 127.548);
            Demo demo = new Demo();
            demo.saveResult(result);
            
            demo.serialize("test.ser");
            Demo restoredDemo = Demo.deserialize("test.ser");

            if (result.toString().equals(restoredDemo.getResult().toString())) {
                System.out.println("Serialization and deserialization test passed.");
            } else {
                System.out.println("Serialization and deserialization test failed.");
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        } 
    }

    @Test
    public void testViewSave() {
        ViewResult viewResult = new ViewResult();
        try {
            viewResult.viewSave();
        } catch (IOException e) {
            e.printStackTrace();
        }
        assertEquals("items.ser", ViewResult.getFNAME());
    }
}

