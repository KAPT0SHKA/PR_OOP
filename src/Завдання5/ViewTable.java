package Завдання5;

import java.util.List;

public class ViewTable extends ViewableTable {

    private List<String> headers;
    private List<List<String>> data;

    public ViewTable(List<String> headers, List<List<String>> data) {
        this.headers = headers;
        this.data = data;
    }

    @Override
    public void displayTable() {
        // Визначення максимальної ширини для кожного стовпця
        int[] columnWidths = new int[headers.size()];
        for (int i = 0; i < headers.size(); i++) {
            columnWidths[i] = headers.get(i).length();
        }
        for (List<String> row : data) {
            for (int i = 0; i < row.size(); i++) {
                columnWidths[i] = Math.max(columnWidths[i], row.get(i).length());
            }
        }

        // Виведення заголовків
        for (int i = 0; i < headers.size(); i++) {
            System.out.printf("%-" + (columnWidths[i] + 2) + "s", headers.get(i));
        }
        System.out.println();

        // Виведення розділювачів під заголовками
        for (int i = 0; i < headers.size(); i++) {
            for (int j = 0; j < columnWidths[i]; j++) {
                System.out.print("-");
            }
            System.out.print("  ");
        }
        System.out.println();

        // Виведення рядків даних
        for (List<String> row : data) {
            for (int i = 0; i < row.size(); i++) {
                System.out.printf("%-" + (columnWidths[i] + 2) + "s", row.get(i));
            }
            System.out.println();
        }
    }
}