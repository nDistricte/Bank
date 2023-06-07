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
    public abstract int DisplayBalance();
    public abstract void transaction();
    public abstract boolean menu();
}