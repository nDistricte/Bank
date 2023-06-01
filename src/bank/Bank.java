/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package bank;
import java.io.IOException;
import java.util.*;
/**
 *
 * @author anatol
 */
public class Bank implements Manager {
    
    
    Scanner input = new Scanner(System.in);

    public void Register(){
    }

    
    
    @Override
    public void transfer(String src, String dest, int amount) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    public boolean Login(){
        System.out.println("Your ID:");
        String ID = input.next();
        System.out.println("password:");
        String password = input.next();
        // Needed SQL query to compare input data with the actual data to acccess
        // If statement needed later on 
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
        this.DisplayList();
        break;
        }
        return exit;
    }
    
    public static void main(String[] args) {
    Bank wow = new Bank();
    boolean exit = false;
    while(!exit){
    exit = wow.menu();
    }
    }

    @Override
    public void displayBal() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }


}
