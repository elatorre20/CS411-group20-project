package game;


import java.sql.*;
import java.util.Scanner;


/**
 * 
 * @author group20
 *
 * Manages checking customer payment information against the customer database
 */
public class PaymentProcessor {
  
  private static String DBAddress = "jdbc:sqlserver://cs411-group-20.database.windows.net:1433;database=arcade;user=group20@cs411-group-20;password=BUcs411g20;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;";
  public static String getPlayer = "SELECT * FROM customers WHERE firstName=";
  public Scanner shell;
  public Connection connection;
  private String userId;
  private float userBalance;
  
  public PaymentProcessor() {
    try {
      this.shell = new Scanner(System.in);
      System.out.println("Two player game? (Y/N): ");
      String numOfPlayers;
      numOfPlayers = this.shell.nextLine().toLowerCase();
      if(numOfPlayers.equals("admin")) {
        this.userBalance = 0;
        this.connection = DriverManager.getConnection(PaymentProcessor.DBAddress);
        while(this.userBalance < 0.5) {
          System.out.println("Enter customer name: ");
          this.userId = this.shell.nextLine().toLowerCase();
          Statement s = this.connection.createStatement();
          ResultSet r = s.executeQuery(PaymentProcessor.getPlayer+"'"+this.userId+"'"); //create query for the selected player's balance
          if(r.next() == false){
            System.out.println("customer has no account, please select a different customer");
            continue;
          }
          this.userBalance = r.getFloat("balance");
          System.out.println(this.userId + "'s current balance: " + String.valueOf(this.userBalance));
          System.out.println("Enter amount to be added to " + this.userId + "'s balance: ");
          float money;
          money = this.shell.nextFloat();
          this.updateBalance(this.userBalance + money);
          System.out.println(this.userId + "'s new balance: " + String.valueOf(this.userBalance));
          System.out.println("Value updated successfully. You may now exit the program. ");
          while(true) {
            String exitHelper = "";
            exitHelper = this.shell.nextLine();
            if(exitHelper.equals("") == false) {
              System.exit(0);
            }
          }
        }
      }
      else if(numOfPlayers.charAt(0) == 'y') {
        Game.onePlayer = false;
      }
      else {
        Game.onePlayer = true;
      }
      this.userBalance = 0;
      this.connection = DriverManager.getConnection(PaymentProcessor.DBAddress);
      while(this.userBalance < 0.5) {
        System.out.println("Enter player 1 first name: ");
        this.userId = this.shell.nextLine().toLowerCase(); //get customer name from console, equivalent to swiping card on physical arcade machine
        Statement s = this.connection.createStatement();
        ResultSet r = s.executeQuery(PaymentProcessor.getPlayer+"'"+this.userId+"'"); //create query for the selected player's balance
        if(r.next() == false){
          System.out.println("customer has no account, please select a different customer");
          continue;
        }
        this.userBalance = r.getFloat("balance");
          if(this.userBalance >= 0.5) {
            System.out.println(this.userId + "'s" + " remaining balance: " + "$" + String.valueOf(this.userBalance-0.5));
            this.updateBalance(this.userBalance - (float)0.5);
            Game.player1Name = this.userId;
            Game.player2Name = "PONG AI";
            break;
          }
        System.out.println("Customer balance too low, please select a different customer or increase balance");
      }
      if(Game.onePlayer == false) {
        this.userBalance = 0;
        while(this.userBalance < 0.5) {
          System.out.println("Enter player 2 first name: ");
          this.userId = this.shell.nextLine().toLowerCase(); //get customer name from console, equivalent to swiping card on physical arcade machine
          Statement s2 = this.connection.createStatement();
          ResultSet r2 = s2.executeQuery(PaymentProcessor.getPlayer+"'"+this.userId+"'"); //create query for the selected player's balance
          if(r2.next() == false){
            System.out.println("customer has no account, please select a different customer");
            continue;
          }
          this.userBalance = r2.getFloat("balance");
            if(this.userBalance >= 0.5) {
              System.out.println(this.userId + "'s" + " remaining balance: " + "$" + String.valueOf(this.userBalance-0.5));
              this.updateBalance(this.userBalance - (float)0.5);
              Game.player2Name = this.userId;
              break;
            }
          System.out.println("Customer balance too low, please select a different customer or increase balance");
        }
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
      ResultSet r = s.executeQuery(PaymentProcessor.getPlayer+"'"+this.userId+"'");
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
      s.execute("UPDATE customers SET balance=" + String.valueOf(newBalance) +" WHERE firstName=" + "'"+this.userId+"'");
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
