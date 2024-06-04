package codekata.kata12bestsellers;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TopTenListUpdater {

    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/my_database";
    private static final String USER = "asd";
    private static final String PASSWORD = "asd";
    private static final long UPDATE_INTERVAL = 3600000;
    private static final int MAX_ITERATIONS = 24;


    public static void main(String[] args) {
        for (int iteration = 0; iteration < MAX_ITERATIONS; iteration++) {
            updateTopTenList();
            sleep(UPDATE_INTERVAL);
        }
    }

    private static void updateTopTenList() {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, USER, PASSWORD)) {
            List<String> topTenList = queryTopTenList(connection);
            displayTopTenList(topTenList);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static List<String> queryTopTenList(Connection connection) throws SQLException {
        List<String> topTenList = new ArrayList<>();

        String query = "SELECT product_id, SUM(quantity_sold) as total_sold " +
                "FROM Sales " +
                "WHERE timestamp >= NOW() - INTERVAL 24 HOUR " +
                "GROUP BY product_id " +
                "ORDER BY total_sold DESC " +
                "LIMIT 10";

        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                String productId = resultSet.getString("product_id");
                int totalSold = resultSet.getInt("total_sold");
                topTenList.add(productId + " (" + totalSold + " sold)");
            }

        }

        return topTenList;
    }

    private static void displayTopTenList(List<String> topTenList) {
        System.out.println("Top Ten List:");
        for (String entry : topTenList) {
            System.out.println(entry);
        }
        System.out.println("_______________________");
    }

    private static void sleep(long milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
