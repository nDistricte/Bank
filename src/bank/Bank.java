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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.transform.OutputKeys;
/**
 *
 * @author anatol
 */
public class Bank implements Manager {
       
    Scanner input = new Scanner(System.in);
    Connection connection = null;
    private User currentUser;
    private boolean exit = false;
       
    
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
    
    public void transaction(String dest, int amount){
        try{
            synchronized(this){
            ConnectDB();
            
            TransactionIDGenerator generatedID = new TransactionIDGenerator();
            String transactionID = generatedID.generatedTransactionID();
                      
        // Deduct the amount from the source user's balance        
        PreparedStatement statement1 = connection.prepareStatement("UPDATE User SET Balance = Balance - ?, Transactions = ? WHERE UserID = ?");
        statement1.setInt(1, amount);
        statement1.setString(2, transactionID);
        statement1.setString(3, currentUser.getID());
        statement1.executeUpdate();
        
        // Add the amount to the destination user's balance
        PreparedStatement statement2 = connection.prepareStatement("UPDATE User SET Balance = Balance + ?, Transactions = ? WHERE UserID = ?");
        statement2.setInt(1, amount);
        statement2.setString(2, transactionID);
        statement2.setString(3, dest);
        statement2.executeUpdate();
       
        //We need to store every transaction from its source to its destination
        PreparedStatement statement3 = connection.prepareStatement("INSERT INTO Transactions (TransactionID, UserID) VALUES (?, ?)");
        statement3.setString(1, transactionID);
        statement3.setString(2, currentUser.getID());
        statement3.executeUpdate();
        
        PreparedStatement statement4 = connection.prepareStatement("INSERT INTO Transactions (TransactionID, UserID) VALUES (?, ?)");
        statement4.setString(1, transactionID);
        statement4.setString(2, dest);
        statement4.executeUpdate();
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        finally{
        try{
            CloseDB();
            }
        catch(SQLException ex){
            ex.printStackTrace();
            }
        }
    }
    
    public User Login(){
    boolean invalid = false;
    User user = null;
    try {
        while (user == null) {
            ConnectDB();

            System.out.println("Your User ID: ");
            String userID = input.next();
            System.out.println("\npassword: ");
            String password = input.next();

            PreparedStatement statement = connection.prepareStatement("SELECT * FROM User WHERE UserID = ? AND Password = ?");
            statement.setString(1, userID);
            statement.setString(2, password);
            ResultSet result = statement.executeQuery();

            if (result.next()) {
                invalid = true;
                //Saving the data from the db into variables to re-use it
                String firstName = result.getString("fName");
                String lastName = result.getString("lName");
                String email = result.getString("Email");

                String dateString = result.getString("DateOfBirth");
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
                java.util.Date utilDateOfBirth;
                try {
                    utilDateOfBirth = dateFormat.parse(dateString);
                } catch (ParseException e) {
                    e.printStackTrace();
                    // Handle the parse exception appropriately
                    return null;
                }

                Calendar calendar = Calendar.getInstance();
                calendar.setTime(utilDateOfBirth);

                DateOfBirth dob = new DateOfBirth(
                    calendar.get(Calendar.DAY_OF_MONTH),
                    calendar.get(Calendar.MONTH) + 1, // Months in Calendar class are 0-based
                    calendar.get(Calendar.YEAR)
                );

                String userPassword = result.getString("Password");
                String UserID = result.getString("UserID");
                int balance = result.getInt("Balance");
                
                user = new User(firstName, lastName, email, dob, userPassword, balance, userID);
                currentUser = user;
                
                System.out.println("\nLogin was successful");
            } else {
                System.out.println("\nInvalid user ID or password");
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();        
    }
    finally{
        try{
            CloseDB();
            }
        catch(SQLException ex){
            ex.printStackTrace();
            }
        }
    return user;
}
    
    public void DisplayBalance(){
        try{
        ConnectDB();
        PreparedStatement statement = connection.prepareStatement("SELECT Balance FROM USER Where UserID = ?");
        statement.setString(1, currentUser.getID());
        ResultSet result = statement.executeQuery();
        int Balance = result.getInt("Balance");
            System.out.println("\nYour Balance: " + Balance);
        }
        catch(SQLException e){
        e.printStackTrace();
        }
        finally{
            try{
        CloseDB();
        }
        catch(SQLException ex){
                ex.printStackTrace();
                }
        }
    }
       
    public void ChangeData(){
        try{
        ConnectDB();
        
        String FName = currentUser.getFName();
        String LName = currentUser.getLName();
        String Email = currentUser.getEmail();
        String Password = currentUser.getPassword();
        
        System.out.printf("\nPress 1 to change your first name: %s", FName);
        System.out.printf("\nPress 2 to change your last name: %s", LName);
        System.out.printf("\nPress 3 to change your Email: %s", Email);
        System.out.printf("\nPress 4 to change your Password %s", Password);
        
        int choice = input.nextInt();
        switch(choice){
            case 1:
                System.out.println("Please enter your first name: ");
                String newFName = input.next();
                PreparedStatement statement1 = connection.prepareStatement("UPDATE User SET fName = ? WHERE UserID = ?");
                statement1.setString(1, newFName);
                statement1.setString(2, currentUser.getID());
                statement1.executeUpdate();
                break;
            case 2:
                System.out.println("Please enter your last name: ");
                String newLName = input.next();
                PreparedStatement statement2 = connection.prepareStatement("UPDATE User SET lName = ? WHERE UserID = ?");
                statement2.setString(1, newLName);
                statement2.setString(2, currentUser.getID());
                statement2.executeUpdate();
                break;
            case 3:
                System.out.println("Please enter your Email: ");
                String newEmail = input.next();
                PreparedStatement statement3 = connection.prepareStatement("UPDATE User SET Email = ? WHERE UserID = ?");
                statement3.setString(1, newEmail);
                statement3.setString(2, currentUser.getID());
                statement3.executeUpdate();
                break;
            case 4:
                System.out.println("Please enter your new password: ");
                String newPassword = input.next();
                PreparedStatement statement4 = connection.prepareStatement("UPDATE User SET Password = ? WHERE UserID = ?");
                statement4.setString(1, newPassword);
                statement4.setString(2, currentUser.getID());
                statement4.executeUpdate();
                break;
            default:
                System.out.println("Invalid Choice");
            }
        } 
        catch(SQLException e){
        e.printStackTrace();
            }    
        finally{
        try{
            CloseDB();
            }
        catch(SQLException ex){
            ex.printStackTrace();
            }
        }
    }
    
public boolean menu() {
    boolean exit = false;
    GUI Window = new GUI();
    
    System.out.println("Welcome");
    System.out.println("\nPress 1 to Register");
    System.out.println("Press 2 to Login");
    System.out.println("Press 3 for the GUI");
    
    int choice1 = input.nextInt();
    
    switch (choice1) {
        case 1:
            Register();
            break;
        case 2:
            
            currentUser =Login();
            
            while (!exit && currentUser != null) {
                System.out.println("\nPress 1 to do a transaction");
                System.out.println("Press 2 to display your balance");
                System.out.println("Press 3 to Change your personal Data");
                System.out.println("Press 4 to exit");
                int choice2 = input.nextInt();
                
                switch (choice2) {
                    
                    case 1:                           
                        System.out.print("Enter destination user ID: ");
                        String dest = input.next();
                        System.out.print("Enter amount: ");
                        int amount = input.nextInt();                               
                        transaction(dest, amount);
                        break;                                           
                    case 2:                       
                        DisplayBalance();
                        break;
                    case 3:
                        ChangeData();
                        
                        break;
                    case 4:
                        exit =true;
                        break;
                        
                    default:
                        System.out.println("Invalid choice.");
                        break;
                    }
                 
            System.out.println("\nPress Enter to continue...");
            input.nextLine();
            input.nextLine();
            }
            break;
        default:
            System.out.println("Invalid choice.");
            break;
        
        case 3:
            Window.InitWindow();
            
    }
    return exit;
}


    
    public static void main(String[] args) {
    /*Bank wow = new Bank();
    while(!wow.exit){
    wow.exit = wow.menu();*/
    GUILogin Window = new GUILogin();
    Window.InitWindow();
            }
   
        }
    
