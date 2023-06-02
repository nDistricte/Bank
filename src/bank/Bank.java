/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package bank;
import java.io.*;
import java.util.*;
import java.sql.*;
/**
 *
 * @author anatol
 */
public class Bank implements Manager {
    
    String UserDB = "jdbc:sqlite:Bank.db";    
    Scanner input = new Scanner(System.in);
    Connection connection = null;

    public void Register(){          
         try{
            // Connect to the SQLite database
             connection = DriverManager.getConnection(UserDB);
            
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
             DateOfBirth DoBUser = new DateOfBirth(Day,Month,Year);
             
             System.out.println("Password: ");
             String Password = input.next();
             
             IDGenerator generatedID = new IDGenerator();
             String userID = generatedID.generateID();
             System.out.println(userID);
             
             
             String Reg = "INSERT INTO USER fName, lName, Email, D";
            }
         catch(SQLException e){
             e.printStackTrace();
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
                
    }

    
    
    @Override
    public void transaction(String src, String dest, int amount) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    @Override
    public boolean Login(){
        boolean validation = true;
        System.out.println("Your ID:");
        String IdInput = input.next();
        System.out.println("password:");
        String passwordInput = input.next();
        
        String LoginVal = "SELECT ID, Password FROM User WHERE ID == IdInput AND Password == passwordInput";
        if(LoginVal){
        return true;
        }
        else{
            return false;
        }
        // Needed SQL query to compare input data with the actual data to acccess
        // If statement needed later on 
        
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
    wow.Register();
    //boolean exit = false;
    //while(!exit){
    //exit = wow.menu();
    }
    
    }



