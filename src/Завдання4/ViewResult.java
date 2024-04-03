package Завдання4;

import java.io.Serializable;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 * ConcreteProduct
 * (Шаблон проектування Factory Method)
 * Обчислення функції, збереження та відображення результатів
 * @author xone
 * @version 1.0
 * @see View
 */
public class ViewResult implements View {
    /** Ім'я файлу, що використовується під час серіалізації */
    private static final String FNAME = "items.bin";
    /** Визначає кількість значень для обчислення за промовчанням */
    private static final int DEFAULT_NUM = 10;
    /** Колекція аргументів та результатів обчислень */
    private ArrayList<Demo> items = new ArrayList<Demo>();

    /** Викликає {@linkplain ViewResult#ViewResult(int n) ViewResult(int n)} з параметром {@linkplain ViewResult#DEFAULT_NUM DEFAULT_NUM} */
    public ViewResult() {
        this(DEFAULT_NUM);
    }

    /** Ініціалізує колекцію {@linkplain ViewResult#items}
     * @param n початкова кількість елементів
     */
    public ViewResult(int n) {
        for(int ctr = 0; ctr < n; ctr++) {
            items.add(new Demo());
        }
    }

    /** Отримати значення {@linkplain ViewResult#items}
     * @return поточне значення посилання на об'єкт {@linkplain ArrayList}
     */
    public ArrayList<Demo> getItems() {
        return items;
    }

    /** Обчислює значення функції
     * @param x аргумент обчислюваної функції
     * @return результат обчислення функції
     */
    private double calc(double x) {
        return Math.sin(x * Math.PI / 180);
    }

    /** Обчислює значення функції та зберігає результат у колекції {@linkplain ViewResult#items}
     * @param stepX крок збільшення аргументу
     */
    public void init(double stepX) {
        double x = 0.0;
        for(Demo item : items) {
            item.setXY(x, calc(x));
            x += stepX;
        }
    }

    /** Викликає <b>init(double stepX)</b> з випадковим значенням аргументу
     * {@inheritDoc}
     */
    @Override
    public void viewInit() {
        init(Math.random() * 360.0);
    }

    /** Реалізація методу {@linkplain View#viewSave()} {@inheritDoc} */
    @Override
    public void viewSave() throws IOException {
        ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(FNAME));
        os.writeObject(items);
        os.flush();
        os.close();
    }

    /** Реалізація методу {@linkplain View#viewRestore()} {@inheritDoc} */
    @SuppressWarnings("unchecked")
    @Override
    public void viewRestore() throws Exception {
        ObjectInputStream is = new ObjectInputStream(new FileInputStream(FNAME));
        items = (ArrayList<Demo>) is.readObject();
        is.close();
    }

    /** Реалізація методу {@linkplain View#viewHeader()} {@inheritDoc} */
    @Override
    public void viewHeader() {
        System.out.println("Results:");
    }

    /** Реалізація методу {@linkplain View#viewBody()} {@inheritDoc} */
    @Override
    public void viewBody() {
        for(Demo item : items) {
            System.out.printf("(%.0f; %.3f) ", item.getX(), item.getY());
        }
        System.out.println();
    }

    /** Реалізація методу {@linkplain View#viewFooter()} {@inheritDoc} */
    @Override
    public void viewFooter() {
        System.out.println("End.");
    }

    /** Реалізація методу {@linkplain View#viewShow()} {@inheritDoc} */
    @Override
    public void viewShow() {
        viewHeader();
        viewBody();
        viewFooter();
    }
    public class Demo implements Serializable {
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
}
