package Завдання3;

import java.io.Serializable;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 * ConcreteProduct
 * (Шаблон проектирования Factory Method)
 * Вычисление функции, сохранение и отображение результатов
 * @author xone
 * @version 1.0
 * @see View
 */
public class ViewResult implements View {
    /** Имя файла, используемое при сериализации */
    private static final String FNAME = "items.bin";
    /** Определяет количество значений для вычисления по умолчанию */
    private static final int DEFAULT_NUM = 10;
    /** Коллекция аргументов и результатов вычислений */
    private ArrayList<Demo> items = new ArrayList<Demo>();

    /** Вызывает {@linkplain ViewResult#ViewResult(int n) ViewResult(int n)} с параметром {@linkplain ViewResult#DEFAULT_NUM DEFAULT_NUM} */
    public ViewResult() {
        this(DEFAULT_NUM);
    }

    /** Инициализирует коллекцию {@linkplain ViewResult#items}
     * @param n начальное количество элементов
     */
    public ViewResult(int n) {
        for(int ctr = 0; ctr < n; ctr++) {
            items.add(new Demo());
        }
    }

    /** Получить значение {@linkplain ViewResult#items}
     * @return текущее значение ссылки на объект {@linkplain ArrayList}
     */
    public ArrayList<Demo> getItems() {
        return items;
    }

    /** Вычисляет значение функции
     * @param x аргумент вычисляемой функции
     * @return результат вычисления функции
     */
    private double calc(double x) {
        return Math.sin(x * Math.PI / 180);
    }

    /** Вычисляет значение функции и сохраняет результат в коллекции {@linkplain ViewResult#items}
     * @param stepX шаг приращения аргумента
     */
    public void init(double stepX) {
        double x = 0.0;
        for(Demo item : items) {
            item.setXY(x, calc(x));
            x += stepX;
        }
    }

    /** Вызывает <b>init(double stepX)</b> со случайным значением аргумента
     * {@inheritDoc}
     */
    @Override
    public void viewInit() {
        init(Math.random() * 360.0);
    }

    /** Реализация метода {@linkplain View#viewSave()} {@inheritDoc} */
    @Override
    public void viewSave() throws IOException {
        ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(FNAME));
        os.writeObject(items);
        os.flush();
        os.close();
    }

    /** Реализация метода {@linkplain View#viewRestore()} {@inheritDoc} */
    @SuppressWarnings("unchecked")
    @Override
    public void viewRestore() throws Exception {
        ObjectInputStream is = new ObjectInputStream(new FileInputStream(FNAME));
        items = (ArrayList<Demo>) is.readObject();
        is.close();
    }

    /** Реализация метода {@linkplain View#viewHeader()} {@inheritDoc} */
    @Override
    public void viewHeader() {
        System.out.println("Results:");
    }

    /** Реализация метода {@linkplain View#viewBody()} {@inheritDoc} */
    @Override
    public void viewBody() {
        for(Demo item : items) {
            System.out.printf("(%.0f; %.3f) ", item.getX(), item.getY());
        }
        System.out.println();
    }

    /** Реализация метода {@linkplain View#viewFooter()} {@inheritDoc} */
    @Override
    public void viewFooter() {
        System.out.println("End.");
    }

    /** Реализация метода {@linkplain View#viewShow()} {@inheritDoc} */
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
