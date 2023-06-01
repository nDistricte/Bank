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
    private String Password;
    private int Balance;
    private String ID;
    
    public void setFName(String fName){
    this.fName = fName;
    }
    public void setLName(String lName){
    this.lName = lName;
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
