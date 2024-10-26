/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vv;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;

/**
 *
 * @author trisha deshmukh
 */

   
public class GRAPH2 {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Amount Distribution by category");
            frame.setSize(800, 600);
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

            frame.addWindowListener(new java.awt.event.WindowAdapter() {
                @Override
                public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                    frame.dispose();  // Dispose the frame but keep the application running
                }
            });

            PieDataset dataset = getDatasetFromDatabase();
            JFreeChart chart = ChartFactory.createPieChart(
                "Amount Distribution by category",  // Updated Chart title
                dataset,                            // Data
                true,                               // Include legend
                true,
                false
            );

            // Display the chart in a JPanel
            ChartPanel chartPanel = new ChartPanel(chart);
            frame.add(chartPanel);
            frame.setVisible(true);
        });
    }

    private static PieDataset getDatasetFromDatabase() {
        DefaultPieDataset dataset = new DefaultPieDataset();
        String url = "jdbc:mysql://localhost:3306/vv2";  // Use the new database vv2
        String user = "root";
        String password = "trisha2005";  // Make sure this matches your MySQL password
        String query = "SELECT addedBy, SUM(amount) AS total_amount FROM expenses GROUP BY addedBy";

        try (Connection connection = DriverManager.getConnection(url, user, password);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                String addedBy = resultSet.getString("addedBy");
                double totalAmount = resultSet.getDouble("total_amount");
                dataset.setValue(addedBy, totalAmount);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return dataset;
    }
}

    

