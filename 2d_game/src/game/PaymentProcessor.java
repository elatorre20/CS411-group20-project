package game;


import java.sql.*;
import java.math.*;


/**
 * 
 * @author group20
 *
 * Manages checking customer payment information against the customer database
 */
public class PaymentProcessor {
  
  private static String DBAddress = "jdbc:sqlserver://cs411-group-20.database.windows.net:1433;database=arcade;user=group20@cs411-group-20;password=BUcs411g20;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;";
  private static String User = "group20";
  private static String Password = "BUcs411g20"; //We are aware it is incredibly poor security practice to just include a password as a plaintext in the source code, this is only for demo purposes.
  public static String testQuery = "SELECT * FROM customers";
  public Connection connection;
  
  public PaymentProcessor() {
    try {
//      Class.forName("com.mysql.jdbc.Driver");
//      this.connection = DriverManager.getConnection(PaymentProcessor.DBAddress, PaymentProcessor.User, PaymentProcessor.Password);
      this.connection = DriverManager.getConnection(PaymentProcessor.DBAddress);
      Statement s = this.connection.createStatement();
      ResultSet r = s.executeQuery(PaymentProcessor.testQuery);
      while(r.next()) {
        System.out.println("name: " + r.getString("firstName"));
      }
    }
//    catch(ClassNotFoundException e) {
//      System.out.println("failed to load JDBC driver");
//      e.printStackTrace();
//    } 
    catch (SQLException e) {
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
