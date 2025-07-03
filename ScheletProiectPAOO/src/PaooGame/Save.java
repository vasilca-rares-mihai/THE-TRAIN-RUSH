package PaooGame;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

public class Save {
//functie care initializeaza tabel
    public void bazaDeDate() {
        createTable();
    }

    //metoda create table
    private static void createTable() {
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:program.db");
            stmt = c.createStatement();
            String sql = "CREATE TABLE IF NOT EXISTS Save3 " +
                    "(col1 INT NOT NULL, " +
                    " col2 INT NOT NULL, " +
                    " col3 INT NOT NULL, " +
                    " col4 INT NOT NULL, " +
                    " col5 INT NOT NULL, " +
                    " col6 DOUBLE NOT NULL, " +
                    " col7 DOUBLE NOT NULL, " +
                    " col8 DOUBLE NOT NULL, " +
                    " col9 STRING NOT NULL)";
            stmt.execute(sql);
            System.out.println("Table created successfully");
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        } finally {
            try {
                if (stmt != null) stmt.close();
                if (c != null) c.close();
            } catch (SQLException e) {
                System.err.println(e.getClass().getName() + ": " + e.getMessage());
            }
        }
    }

    // metoda care insereaza in baza de date
    public static void insertData(int col1, int col2, int col3, int col4, int col5, double col6, double col7, double col8, String col9) {
        Connection c = null;
        PreparedStatement pstmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:program.db");
            String sql = "INSERT INTO Save3 (col1, col2, col3, col4, col5, col6, col7, col8, col9) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            pstmt = c.prepareStatement(sql);
            pstmt.setInt(1, col1);
            pstmt.setInt(2, col2);
            pstmt.setInt(3, col3);
            pstmt.setInt(4, col4);
            pstmt.setInt(5, col5);
            pstmt.setDouble(6, col6);
            pstmt.setDouble(7, col7);
            pstmt.setDouble(8, col8);
            pstmt.setString(9, col9);
            pstmt.executeUpdate();
            System.out.println("Data inserted successfully");
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        } finally {
            try {
                if (pstmt != null) pstmt.close();
                if (c != null) c.close();
            } catch (SQLException e) {
                System.err.println(e.getClass().getName() + ": " + e.getMessage());
            }
        }
    }

    // metoda care extrage din baza de date
    public static List<String[]> getData() {
        List<String[]> data = new ArrayList<>();
        Connection c = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:program.db");
            stmt = c.createStatement();
            rs = stmt.executeQuery("SELECT * FROM Save3");

            while (rs.next()) {
                int col1 = rs.getInt("col1");
                int col2 = rs.getInt("col2");
                int col3 = rs.getInt("col3");
                int col4 = rs.getInt("col4");
                int col5 = rs.getInt("col5");
                double col6 = rs.getDouble("col6");
                double col7 = rs.getDouble("col7");
                double col8 = rs.getDouble("col8");
                String col9 = rs.getString("col9");

                String[] row = new String[] {
                        String.valueOf(col1),
                        String.valueOf(col2),
                        String.valueOf(col3),
                        String.valueOf(col4),
                        String.valueOf(col5),
                        String.valueOf(col6),
                        String.valueOf(col7),
                        String.valueOf(col8),
                        String.valueOf(col9)
                };
                System.out.println("Retrieved row: " + Arrays.toString(row)); // Log retrieved row
                data.add(row);
            }
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (c != null) c.close();
            } catch (SQLException e) {
                System.err.println(e.getClass().getName() + ": " + e.getMessage());
            }
        }
        return data;
    }
}