package game;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

/**
 * Writes the score digits
 * <p>
 * @author Group 20 
 * @since 0.1.4
 */

public class Score {
  
  public static String digits;
  public static int score;
  
  /**
   * Draws a block digit, used for score counters
   * <p>
   * digits are 50x70 pixels
   * @param the game window
   * @param the Graphics object
   * @param the digit to draw. values other than 0 to 9 will be ignored
   * @param the x coordinate of the top left corner of the digit
   * @param the y coordinate of the top left corner of the digit
   */  
  public static void drawDigit(GameContainer window, Graphics g, char d, int x, int y) {
    switch(d) {
      case '0':
        g.fillRect(x, y, 50, 5);
        g.fillRect(x, y, 5, 70);
        g.fillRect(x, y+65, 50, 5);
        g.fillRect(x+45, y, 5, 70);
        break;
      case '1':
        g.fillRect(x+45, y, 5, 70);
        break;
      case '2':
        g.fillRect(x, y, 50, 5);
        g.fillRect(x+45, y, 5, 35);
        g.fillRect(x, y+35, 50, 5);
        g.fillRect(x, y+35, 5, 35);
        g.fillRect(x, y+65, 50, 5);
        break;
      case '3':
        g.fillRect(x, y, 50, 5);
        g.fillRect(x, y+35, 50, 5);
        g.fillRect(x, y+65, 50, 5);
        g.fillRect(x+45, y, 5, 70);
        break;
      case '4':
        g.fillRect(x, y, 5, 35);
        g.fillRect(x, y+35, 50, 5);
        g.fillRect(x+45, y, 5, 70);
        break;
      case '5':
        g.fillRect(x, y, 50, 5);
        g.fillRect(x, y, 5, 35);
        g.fillRect(x, y+35, 50, 5);
        g.fillRect(x+45, y+35, 5, 35);
        g.fillRect(x, y+65, 50, 5);
        break;
      case '6':
        g.fillRect(x, y, 50, 5);
        g.fillRect(x+45, y+35, 5, 35);
        g.fillRect(x, y+35, 50, 5);
        g.fillRect(x, y, 5, 70);
        g.fillRect(x, y+65, 50, 5);
        break;
      case '7':
        g.fillRect(x+45, y, 5, 70);
        g.fillRect(x, y, 50, 5);
        break;
      case '8':
        g.fillRect(x, y, 50, 5);
        g.fillRect(x, y, 5, 70);
        g.fillRect(x, y+35, 50, 5);
        g.fillRect(x, y+65, 50, 5);
        g.fillRect(x+45, y, 5, 70);
        break;
      case '9':
        g.fillRect(x, y, 50, 5);
        g.fillRect(x, y, 5, 35);
        g.fillRect(x, y+35, 50, 5);
        g.fillRect(x+45, y, 5, 70);
      default:
        break;
    }
    
  }
  
  /**
   * Draws a number in block digits, used for score counters
   * <p>
   * digits are 50x70 pixels
   * @param the game window
   * @param the Graphics object
   * @param the string to draw. values other than 0 to 9 will be ignored
   * @param the x coordinate of the top left corner of the digit
   * @param the y coordinate of the top left corner of the digit
   */  
  public static void drawString(GameContainer window, Graphics g, String d, int x, int y) {
    for(int i = 0; i < d.length(); i++) {
      drawDigit(window, g, d.charAt(i), x +(60*i), y);
    }
  }
  
  public static void drawNet(GameContainer window, Graphics g, int step, int gap) {
    int x = window.getWidth()/2;
    int y = window.getHeight();
    for(int i = 0; i < y; i += (step+gap)) {
      g.fillRect(x-2, i, 4, step);
    }
  }

}
