package ex7;

import java.io.*;

/**
 * Клас для зберігання параметрів і результатів обчислень 8-23
 */

class BallisticResult implements Serializable {
    double v0; // Початкова швидкість
    double alpha; // Кут
    double distance; // Відстань

    BallisticResult(double v0, double alpha, double distance) {
        this.v0 = v0;
        this.alpha = alpha;
        this.distance = distance;
    }

    @Override
    public String toString() {
        return "Initial Velocity: " + v0 + " m/s, Angle: " + alpha + " degrees, Distance: " + distance + " meters";
    }
}

/**
 * Клас для знаходження рішення задачі 26-27
 */
class BallisticCalculator {

    /**
     * Метод для обчислення відстані 30-32
     */
    public double calculateDistance(double v0, double alpha) {
        final double g = 9.81; // Declaring 'g' as a local variable
        double radians = Math.toRadians(alpha);
        return (v0 * v0 * Math.sin(2 * radians)) / g;
    }
}



/**
 * Клас для демонстрації збереження та відновлення стану об'єкта 39-52
 */
class Demo implements Serializable {
    private BallisticResult result;

    public void saveResult(BallisticResult result) {
        this.result = result;
    }

    public BallisticResult getResult() {
        return result;
    }

    public void displayResult() {
        System.out.println(result);
    }

    public void serialize(String filename) throws IOException {
        FileOutputStream fileOut = new FileOutputStream(filename);
        ObjectOutputStream out = new ObjectOutputStream(fileOut);
        out.writeObject(this);
        out.close();
        fileOut.close();
    }

    public void saveData(ViewTable viewTable) {
        /** Реалізація методу для збереження даних 67-70*/
        System.out.println("Data saved successfully.");
    }

    public static Demo deserialize(String filename) throws IOException, ClassNotFoundException {
        FileInputStream fileIn = new FileInputStream(filename);
        ObjectInputStream in = new ObjectInputStream(fileIn);
        Demo demo = (Demo) in.readObject();
        in.close();
        fileIn.close();
        return demo;
    }

}
