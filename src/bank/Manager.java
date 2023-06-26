/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
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
public interface Manager {
    public abstract void Register();
    public abstract User Login();
    public abstract void DisplayBalance();
    public abstract void transaction(String dest, int amount);
    public abstract void ChangeData() throws SQLException;
    public abstract boolean menu();
}