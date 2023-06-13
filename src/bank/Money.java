/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bank;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Currency;

/**
 *
 * @author anatol
 */

/*Currency reader and rounds money
  Code might needs to be re-configured later on
  Creation will be delayed by a while until the rest is working
*/
public class Money {
    private static final Currency USD = Currency.getInstance("USD");
    private static final Currency EURO = Currency.getInstance("EURO");

    
    private final BigDecimal amount;
    private final Currency currency;
}
