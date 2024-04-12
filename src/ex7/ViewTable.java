package ex7;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class ViewTable {
    private List<String> headers;
    private List<List<String>> data;
    private JTable table;

    public ViewTable(List<String> headers, List<List<String>> data) {
        this.headers = headers;
        this.data = data;
    }

    public void createTable() {
        DefaultTableModel model = new DefaultTableModel();
        for (String header : headers) {
            model.addColumn(header);
        }
        for (List<String> rowData : data) {
            model.addRow(rowData.toArray());
        }
        table = new JTable(model);
    }

    public JTable getTable() {
        return table;
    }

    public void displayTable() {
        createTable();
        JFrame frame = new JFrame("Results Table");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.add(new JScrollPane(table));
        frame.pack();
        frame.setVisible(true);
    }
}

