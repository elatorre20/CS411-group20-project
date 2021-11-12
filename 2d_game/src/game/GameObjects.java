package game;

/**
 * Container for all game objects
 * <p>
 * keeps track of the two paddles, ball, and score digits
 * @author Group 20 
 * @since 0.1.0
 */
public class GameObjects {
  
  public Ball ball;
  public Paddle player1;
  public Paddle player2;
  public boolean player2CPU; //True if player2 is controlled by the AI, false if it is controlled by a second player
  
  /**
   * 
   * @param xDimension screen window width
   * @param yDimension screen window height
   * @param difficulty Determines the speed of the ball and paddles,
   * a higher value will make the game more difficult
   */
  public GameObjects(int xDimension, int yDimension, float difficulty) {
    
    this.player2CPU = false;
    this.ball = new Ball("ball", (xDimension / 2), (yDimension / 2), 10, difficulty, xDimension, yDimension);
    this.player1 = new Paddle("player1", 50, (yDimension / 2), 50, difficulty * 2);
    this.player2 = new Paddle("player2", (xDimension-50), (yDimension / 2), 50, difficulty * 2);
    }
    public GameObjects(String gameType, int xDimension, int yDimension) {
      if(gameType.toLowerCase() == "1p" || gameType.toLowerCase() == "1 player") {
        this.player2CPU = true;
      }
      
      else {
        this.player2CPU = false;
      }
      
      this.ball = new Ball("ball", (xDimension / 2), (yDimension / 2), 10, 60, xDimension, yDimension);
      this.player1 = new Paddle("player1", 50, (yDimension / 2), 50, yDimension);
      this.player2 = new Paddle("player2", (xDimension-50), (yDimension / 2), 50, yDimension);
      }
   
   /**
    * Sets the width of the player paddle
    * <p>
    * @param width The width of the paddle
    * @param player 1 for player1, 2 for player2
    */
   public void setPaddleWidth(float width, int player) {
     if(player == 1) {
       this.player1.size = width;
     }
     if(player == 2) {
       this.player2.size = width;
     }
   }
   
   /**
    * Sets the size of the ball
    * <p>
    * @param size size of the ball
    */
   public void setBallSize(float size) {
     this.ball.size = size;
   }

}
