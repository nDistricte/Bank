/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bank;

import java.sql.*;
import java.util.*;
import java.io.*;
/**
 *
 * @author anatol
 */
public class TransactionIDGenerator {
    private static final String CAP_ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String Numbers = "1234567890";
    private static final int Trans_Lenght = 16;
    private static final String Database_URL = "jdbc:sqlite:Bank.db";
    
    public static String generatedTransactionID(){
        String ID = null;
        boolean isUnique = false;
        Connection connection = null;
        try{
            // Connect to the SQLite database
            connection = DriverManager.getConnection(Database_URL);
            
            while(!isUnique){
                StringBuilder sb = new StringBuilder();
                Random random = new Random();
                
                for(int i = 0; i<Trans_Lenght;i++){
                    int randomIndex = random.nextInt(Numbers.length());
                    char randomChar = Numbers.charAt(randomIndex);
                    char randomZif = CAP_ALPHABET.charAt(randomIndex);
                    sb.append(randomChar);
                    sb.append(randomZif);
                }
                ID = sb.toString();
                
              PreparedStatement statement = connection.prepareStatement("SELECT COUNT(*) FROM Transactions WHERE TransactionID = ?");
                statement.setString(1, ID);
                ResultSet result = statement.executeQuery();
                int count  = result.getInt(1);
                
                if(count <= 2){
                    isUnique = true;
                    
                }
            }
        }
        catch(SQLException e){
        e.printStackTrace();
                try {
            connection.rollback(); // Roll back the transaction if an error occurs
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        }
        finally{
        if(connection != null){
            try{
                connection.close();
                }
            catch(SQLException ex){
                ex.printStackTrace();
                }
            }
        }
        return ID;
    }
}
