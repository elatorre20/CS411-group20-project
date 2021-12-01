package game;


import java.sql.*;
import java.util.Scanner;
import java.math.*;


/**
 * 
 * @author group20
 *
 * Manages checking customer payment information against the customer database
 */
public class PaymentProcessor {
  
  private static String DBAddress = "jdbc:sqlserver://cs411-group-20.database.windows.net:1433;database=arcade;user=group20@cs411-group-20;password=BUcs411g20;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;";
  public static String getPlayer = "SELECT * FROM customers WHERE id=";
  public Scanner shell;
  public Connection connection;
  private String userId;
  private float userBalance;
  
  public PaymentProcessor() {
    try {
      this.userBalance = 0;
      this.connection = DriverManager.getConnection(PaymentProcessor.DBAddress);
      this.shell = new Scanner(System.in);
      while(this.userBalance < 0.5) {
        System.out.println("enter customer id: ");
        this.userId = this.shell.nextLine(); //get customer id from console, equivalent to swiping card on physical arcade machine
        Statement s = this.connection.createStatement();
        ResultSet r = s.executeQuery(PaymentProcessor.getPlayer+"'"+this.userId+"'"); //create query for the selected player's balance
        r.next();
        this.userBalance = r.getFloat("balance");
          if(this.userBalance >= 0.5) {
            PaymentProcessor.getPlayer = PaymentProcessor.getPlayer+"'"+this.userId+"'";
            System.out.println(this.userId + " " + String.valueOf(this.userBalance));
            this.updateBalance(this.userBalance - (float)0.5);
            break;
          }
        System.out.println("Customer balance too low, please select a different customer or increase balance");
      }
    }
    catch (SQLException e) {
      System.out.println("SQL error");
      e.printStackTrace();
    }
  }
  
  public void refreshBalance() {
    Statement s;
    try {
      s = this.connection.createStatement();
      ResultSet r = s.executeQuery(PaymentProcessor.getPlayer);
      r.next();
      this.userBalance = r.getFloat("balance");
    } catch (SQLException e) {
      System.out.println("failed to connect to SQL server");
      e.printStackTrace();
    } 
  }
  
  public void updateBalance(float newBalance) {
    Statement s;
    try {
      s = this.connection.createStatement();
//      System.out.println("UPDATE customers SET balance=" + String.valueOf(newBalance) +" WHERE id=" + this.userId);
      s.execute("UPDATE customers SET balance=" + String.valueOf(newBalance) +" WHERE id=" + this.userId);
      this.refreshBalance();
    } catch (SQLException e) {
      System.out.println("failed to connect to SQL server");
      e.printStackTrace();
    } 
  }
  
  protected void finalize() {
    try {
      this.connection.close();
      System.out.println("disconnected from SQL server");
    } catch (SQLException e) {
      System.out.println("failed to connect to SQL server");
      e.printStackTrace();
    }
  }
  
  

}
