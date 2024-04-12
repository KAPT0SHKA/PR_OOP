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

        JTextField velocityField = new JTextField();
        JTextField angleField = new JTextField();
        velocityField.setBounds(5, 25, 150, 50);
        angleField.setBounds(5, 95, 150, 50);
        window.add(velocityField);
        window.add(angleField);

        JButton calculateButton = new JButton("Calculate");
        calculateButton.setBounds(5, 155, 102, 50);
        calculateButton.setBackground(Color.CYAN);
        window.add(calculateButton);

        JButton displayTableButton = new JButton("Display Table");
        displayTableButton.setBounds(120, 155, 150, 50);
        displayTableButton.setBackground(Color.YELLOW);
        window.add(displayTableButton);

        JLabel label = new JLabel("Введіть початкову швидкість (m/s):");
        label.setBounds(5,-15,220,60);
        window.add(label);

        JLabel label2 = new JLabel("Введіть кут запуску:");
        label2.setBounds(5,60,150,50);
        window.add(label2);


        JLabel resultLabel = new JLabel();
        resultLabel.setBounds(5, 185, 400, 50);
        window.add(resultLabel);

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

        displayTableButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayResultsTable();
            }
        });

        resultsQueue = new LinkedList<>();

        window.setVisible(true);
    }

    private static void displayResultsTable() {
        List<String> headers = new ArrayList<>();
        headers.add("Initial Velocity (m/s)");
        headers.add("Launch Angle");
        headers.add("Distance (m)");

        List<List<String>> data = new ArrayList<>();
        for (BallisticResult result : resultsQueue) {
            List<String> row = new ArrayList<>();
            row.add(String.valueOf(result.v0));
            row.add(String.valueOf(result.alpha));
            row.add(String.valueOf(result.distance));
            data.add(row);
        }

        ViewTable viewTable = new ViewTable(headers, data);
        viewTable.displayTable();
    }

}