package ex7;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class ViewTable {
    private List<String> headers;
    private List<List<String>> data;
    private JTable table; // Declare the table variable

    public ViewTable(List<String> headers, List<List<String>> data) {
        this.headers = headers;
        this.data = data;
        createTable(); // Call createTable() method to initialize the table
    }

    private void createTable() {

    }

    public JTable getTable() {
        return table;
    }

    public void displayTable() {
    }
}
