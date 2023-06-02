/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bank;
import java.util.Calendar;
import java.util.Date;

/**
 * Represents a date of birth.
 * Allows validation and manipulation of the date.
 * Provides conversion to `Date` and string representation.
 * 
 * @author anatol
 */
public class DateOfBirth {
    private int day;
    private int month;
    private int year;
    
    public DateOfBirth(int day, int month, int year) {
        if (!isValidDate(day, month, year)) {
            throw new IllegalArgumentException("Invalid date of birth.");
        }
        
        this.day = day;
        this.month = month;
        this.year = year;
    }
    
    public int getDay() {
        return day;
    }
    
    public int getMonth() {
        return month;
    }
    
    public int getYear() {
        return year;
    }
    
    public void setDay(int day) {
        if (!isValidDate(day, month, year)) {
            throw new IllegalArgumentException("Invalid day.");
        }
        
        this.day = day;
    }
    
    public void setMonth(int month) {
        if (!isValidDate(day, month, year)) {
            throw new IllegalArgumentException("Invalid month.");
        }
        
        this.month = month;
    }
    
    public void setYear(int year) {
        if (!isValidDate(day, month, year)) {
            throw new IllegalArgumentException("Invalid year.");
        }
        
        this.year = year;
    }
    
    public Date toDate() {
        Calendar cal = Calendar.getInstance();
        cal.set(year, month - 1, day); // Months in Calendar class are 0-based
        return cal.getTime();
    }
    
    public long getTime() {
        return toDate().getTime();
    }
    
    @Override
    public String toString() {
        return String.format("%02d-%02d-%04d", day, month, year);
    }
    
    private boolean isValidDate(int day, int month, int year) {
        if (month < 1 || month > 12 || day < 1 || day > 31) {
            return false;
        }
        
        Calendar currentDate = Calendar.getInstance();
        currentDate.set(Calendar.HOUR_OF_DAY, 0);
        
        Calendar minimumDate = (Calendar) currentDate.clone();
        minimumDate.add(Calendar.YEAR, -18);
        
        Calendar dateOfBirth = Calendar.getInstance();
        dateOfBirth.set(year, month - 1, day); // Months in Calendar class are 0-based
        
        return !dateOfBirth.after(currentDate) && !dateOfBirth.after(minimumDate);
    }
}
