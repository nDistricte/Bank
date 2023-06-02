/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bank;
import java.io.*;
import java.util.*;
import java.sql.*;
/**
 *
 * @author anatol
 */
public class IDGenerator {
    private static final String Numbers = "1234567890";
    private static final int ID_Lenght = 8;
    private static final String Database_URL = "jdbc:sqlite:Bank.db";
    
    public static String generateID() {
        String ID = null;
        boolean isUnique = false;
        Connection connection = null;
        
        try {
            // Connect to the SQLite database
            connection = DriverManager.getConnection(Database_URL);
            
            // Generate and check for uniqueness
            while (!isUnique) {
                StringBuilder sb = new StringBuilder();
                Random random = new Random();
                
                // Add the starting letter "A"
                sb.append("A");
                
                // Generate the remaining characters randomly
                for (int i = 1; i < ID_Lenght; i++) {
                    int randomIndex = random.nextInt(Numbers.length());
                    char randomChar = Numbers.charAt(randomIndex);
                    sb.append(randomChar);
                }
                
                ID = sb.toString();
                
                // Check if the ID already exists in the database
                PreparedStatement statement = connection.prepareStatement("SELECT COUNT(*) FROM User WHERE UserID = ?");
                statement.setString(1, ID);
                ResultSet resultSet = statement.executeQuery();
                int count = resultSet.getInt(1);
                
                if (count == 0) {
                    isUnique = true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            ID = null;
        } finally {
            // Close the database connection
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        
        return ID;
    }
}
