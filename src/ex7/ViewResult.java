package ex7;

import java.io.*;
import java.util.ArrayList;

public class ViewResult implements View, Serializable {
    private static final long serialVersionUID = 1L;
    private static final String FNAME = "items.ser";
    private static final int DEFAULT_NUM = 10; /** Оголошення константи 9 */
    private ArrayList<Demo> items = new ArrayList<>();

    public ViewResult() {
        this(DEFAULT_NUM); /** Виклик конструктора з власним значенням 12-14 */
    }

    public ViewResult(int n) {
        for (int ctr = 0; ctr < n; ctr++) {
            items.add(new Demo());
        }
    }

    public ArrayList<Demo> getItems() {
        return items;
    }

    private double calc(double x) {
        return Math.sin(x * Math.PI / 180);
    }

    public void init(double stepX) {
        double x = 0.0;
        for (Demo item : items) {
            item.setXY(x, calc(x));
            x += stepX;
        }
    }

    @Override
    public void viewInit() {
        init(Math.random() * 360.0);
    }

    @Override
    public void viewSave() throws IOException {
        try (ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(FNAME))) {
            os.writeObject(items);
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public void viewRestore() throws IOException, ClassNotFoundException {
        try (ObjectInputStream is = new ObjectInputStream(new FileInputStream(FNAME))) {
            items = (ArrayList<Demo>) is.readObject();
        }
    }

    @Override
    public void viewHeader() {
        System.out.println("Results:");
    }

    @Override
    public void viewBody() {
        for (Demo item : items) {
            System.out.printf("(%.0f; %.3f) ", item.getX(), item.getY());
        }
        System.out.println();
    }

    @Override
    public void viewFooter() {
        System.out.println("End.");
    }

    @Override
    public void viewShow() {
        viewHeader();
        viewBody();
        viewFooter();
    }

    public static class Demo implements Serializable {
        private static final long serialVersionUID = 1L;
        private double x;
        private double y;

        public void setXY(double x, double y) {
            this.x = x;
            this.y = y;
        }

        public double getX() {
            return x;
        }

        public double getY() {
            return y;
        }
    }
    
    public static String getFNAME() {
        return FNAME;
    }
    

}
