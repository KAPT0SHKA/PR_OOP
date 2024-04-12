package ex7;

import java.util.LinkedList;
import java.util.Queue;
import java.util.ArrayList;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;



public class app {

    private static Queue<BallisticResult> resultsQueue;

    public static void main(String[] args) {

        JFrame window = new JFrame("BallisticsCalculator");
        window.setBounds(5, 5, 500, 500);
        window.setLayout(null);

        // Text fields for input
        JTextField velocityField = new JTextField();
        JTextField angleField = new JTextField();
        velocityField.setBounds(5, 5, 150, 50);
        angleField.setBounds(5, 65, 150, 50);
        window.add(velocityField);
        window.add(angleField);

        // Button for calculation
        JButton calculateButton = new JButton("Calculate");
        calculateButton.setBounds(5, 125, 102, 50);
        calculateButton.setBackground(Color.CYAN);
        window.add(calculateButton);

        // Button for displaying table
        JButton displayTableButton = new JButton("Display Table");
        displayTableButton.setBounds(120, 125, 150, 50);
        displayTableButton.setBackground(Color.YELLOW);
        window.add(displayTableButton);

        // Label for displaying result
        JLabel resultLabel = new JLabel();
        resultLabel.setBounds(5, 185, 400, 50);
        window.add(resultLabel);

        // Event handling for calculation button click
        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    double v0 = Double.parseDouble(velocityField.getText());
                    double alpha = Double.parseDouble(angleField.getText());
                    BallisticCalculator calculator = new BallisticCalculator();
                    double distance = calculator.calculateDistance(v0, alpha);
                    resultLabel.setText("Distance: " + distance + " meters");
                    // Add the result to the queue
                    BallisticResult result = new BallisticResult(v0, alpha, distance);
                    resultsQueue.offer(result);
                } catch (NumberFormatException ex) {
                    resultLabel.setText("Invalid input. Please enter numeric values.");
                }
            }
        });

        // Event handling for displaying table button click
        displayTableButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayResultsTable();
            }
        });

        // Initialize the queue
        resultsQueue = new LinkedList<>();

        window.setVisible(true);
    }

    private static void displayResultsTable() {
        // Create headers for the table
        List<String> headers = new ArrayList<>();
        headers.add("Initial Velocity (m/s)");
        headers.add("Launch Angle");
        headers.add("Distance (m)");

        // Create data for the table
        List<List<String>> data = new ArrayList<>();
        for (BallisticResult result : resultsQueue) {
            List<String> row = new ArrayList<>();
            row.add(String.valueOf(result.v0));
            row.add(String.valueOf(result.alpha));
            row.add(String.valueOf(result.distance));
            data.add(row);
        }

        // Create the table view
        ViewTable viewTable = new ViewTable(headers, data);

        // Display the table in a dialog
        JOptionPane.showMessageDialog(null, new JScrollPane(viewTable.getTable()), "Results Table", JOptionPane.PLAIN_MESSAGE);
    }
}
