/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package bank;
import java.io.*;
import java.math.BigDecimal;
import java.util.*;
import java.sql.*;
import java.time.LocalDate;
import java.sql.Date;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author anatol
 */
public class Bank implements Manager {
       
    Scanner input = new Scanner(System.in);
    Connection connection = null;
    
    
    public void ConnectDB() throws SQLException{
        // Connect to the SQLite database
    String UserDB = "jdbc:sqlite:Bank.db";
    connection = DriverManager.getConnection(UserDB);
    }
    public void CloseDB() throws SQLException{
              // Close the database connection
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
    }

    public void Register(){          
         try{
            // Connect to the SQLite database
             ConnectDB();
            
             System.out.println("\nFirst name: ");
             String fName = input.next();
             
             System.out.println("\nLast name: ");
             String lName = input.next();
             
             System.out.println("\nEmail: ");
             String Email = input.next();
              
             System.out.println("\nDate of Birth");
             System.out.println("Day: ");
             int Day = input.nextInt();
             System.out.println("Month: ");
             int Month = input.nextInt();
             System.out.println("Year: ");
             int Year = input.nextInt();  
             
             String dateOfBirth = String.format("%02d.%02d.%04d", Day, Month, Year);

             System.out.println("Date of Birth: " + dateOfBirth);
             
             System.out.println("\nPassword: ");
             String Password = input.next();
             
             IDGenerator generatedID = new IDGenerator();
             String userID = generatedID.generateID();
             int Balance = 0;
             
             
             PreparedStatement statement = connection.prepareStatement("INSERT INTO USER (fName, lName, Email, DateOfBirth, Password, UserID, Balance) VALUES (?, ?, ?, ?, ?, ?, ?)");
             statement.setString(1, fName);
             statement.setString(2, lName);
             statement.setString(3, Email);
             statement.setString(4, dateOfBirth);
             statement.setString(5, Password);
             statement.setString(6, userID);
             statement.setInt(7, Balance);
             statement.executeUpdate();
            
             System.out.println("\nthat is your User ID: " + userID + "\n");
            }
         // catches errors and returns them
         catch(SQLException e){
             e.printStackTrace();
         } finally {
             try {
                 CloseDB();
             } catch (SQLException e) {
                 e.printStackTrace();
             }
        }
                
    }
    
    
    //Maybe want to include threads?
    public void transaction(String src, String dest, int amount){
        try{
            ConnectDB();  
                      
            PreparedStatement statement1 = connection.prepareStatement("SELECT UserID, Balance - ? AS Balance FROM User WHERE UserID = ?");
            statement1.setInt(1, amount);
            statement1.setString(2, src);
            statement1.execute(); 
            
            /*PreparedStatement statement2 = connection.prepareStatement("SELECT UserID, Balance + ? AS Balance FROM User Where UserID = ?");
            statement2.setInt(1, amount);
            statement2.setString(2, dest);
            statement2.execute();*/
        }
        catch(SQLException e){
            e.printStackTrace();
        } finally{
            try {
                CloseDB();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
       
    }
    
    public boolean Login(){
        boolean invalid = false;
        try{
         while(!invalid){
            ConnectDB();
            
            System.out.println("Your User ID: ");
            String UserID = input.next();
            System.out.println("\npassword: ");
            String password = input.next();
         
         
           PreparedStatement statement = connection.prepareStatement("SELECT * FROM User WHERE UserID = ? AND Password = ?");
           statement.setString(1, UserID);
           statement.setString(2, password);
           ResultSet result = statement.executeQuery();           
           
         if(result.next()){
             invalid = true;
             System.out.println("\nLogin was succesfull");
            }
         else{
             System.out.println("\n invalid \n");
             }
         }
        }
        catch(SQLException e){
        e.printStackTrace();        
        }
       
        finally{
            try {
                 CloseDB();
             } catch (SQLException e) {
                 e.printStackTrace();
             }
        }
        return invalid = false;                       
    }
    
    public int DisplayBalance(){
        return 1;
    }
    
    public boolean menu(){
    boolean exit = false;
    
        System.out.println("Welcome");
        System.out.println("\nPress 1 for adding a user");
        System.out.println("Press 2 for displaying");
        
        int choise = input.nextInt();
        
        switch(choise){
        
        case(1):
            int Balance = input.nextInt();
            String ID = input.next();
        
        case(2):
        break;
        }
        return exit;
    }
    
    public static void main(String[] args) {
    Bank wow = new Bank();
    wow.transaction("A7087718", "A8114290", 25);
    //wow.Login();
    //boolean exit = false;
    //while(!exit){
    //exit = wow.menu();
    }
    
    }



