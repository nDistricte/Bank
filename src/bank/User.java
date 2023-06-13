/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bank;

/**
 *
 * @author anatol
 */
public class User {
    private String fName;
    private String lName;
    private String Email;
    private DateOfBirth DoB;
    private String Password;
    private int Balance; 
    private String ID;

    public User(String fName,String lName, String Email, DateOfBirth DoB, String Password, int Balance, String ID){
    this.fName = fName;
    this.fName = lName;
    this.Email = Email;
    this.DoB = DoB;
    this.Password = Password;
    this.Balance = Balance;
    this.ID = ID;
    }
    public User(String ID,String Password){
    this.ID = ID;
    this.Password = Password;
    }
    
    public void setFName(String fName){
    this.fName = fName;
    }
    public void setLName(String lName){
    this.lName = lName;
    }
    public void setEmail(String Email){
    this.Email = Email;
    }
    public void setDoB(DateOfBirth DoB){
    this.DoB = DoB;
    }
    public void setPassword(String Password){
    this.Password = Password;
    }
    public void setBalance(int Balance){
    this.Balance = Balance;
    }
    public void setID(String ID){
    this.ID = ID;
    }
    
    public String getFName(){
    return fName;
    }
    public String getLName(){
    return lName;
    }
    public String getEmail(){
    return Email;
    }
    public DateOfBirth getDoB(){
    return DoB;
    }
    public String getPassword(){
    return Password;
    }
    public int getBalance(){
    return Balance;
    }
    public String getID(){
    return ID;
    }
        
    public void deposite(int amount){
        Balance = Balance + amount;
    }
    
    public void withdraw(int amount){
    Balance = Balance - amount;
    }
    
}

