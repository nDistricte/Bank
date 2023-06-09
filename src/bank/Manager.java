/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package bank;

/**
 *
 * @author anatol
 */
public interface Manager {
    public abstract void Register();
    public abstract boolean Login();
    public abstract void DisplayBalance();
    public abstract void transaction(String src, String dest, int amount);
    public abstract boolean menu();
}