/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bank;

import java.awt.*;
import javax.swing.*;
import java.text.*;
import java.awt.event.*;
/**
 *
 * @author anatol
 */
public class GUILogin implements ActionListener{

    public void InitWindow() {
        
    JFrame Window = new JFrame();
    JPanel Panel = new JPanel();
    
    Window.setSize(350,200);
    Window.setTitle("Anatol Bank");
    Window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    Window.add(Panel);
    
    Panel.setLayout(null);
    JLabel UserLabel = new JLabel("Username: ");
    UserLabel.setBounds(10,20,80,25);
    Panel.add(UserLabel);
    
    JTextField UserInputField = new JTextField(20);
    UserInputField.setBounds(100,20,165,25);
    Panel.add(UserInputField);
    
    JLabel PasswordLabel = new JLabel("Password: ");
    PasswordLabel.setBounds(10,50,80,25);
    Panel.add(PasswordLabel);
    
    JPasswordField PasswordInputField =new JPasswordField();
    PasswordInputField.setBounds(100,50,165,25);
    Panel.add(PasswordInputField);
    
    JButton LoginButton = new JButton("Login");
    LoginButton.setBounds(10,80,80,25);
    Panel.add(LoginButton);
    LoginButton.addActionListener(this);
    
    Window.setLocationRelativeTo(null);
    Window.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("Fridi");
    }
        
    
}
