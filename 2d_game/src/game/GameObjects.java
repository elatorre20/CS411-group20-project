package game;

public class GameObjects {
  
  public GameObject ball;
  public GameObject player1;
  public GameObject player2;
  public boolean player2CPU; //True if player2 is controlled by the AI, false if it is controlled by a second player
  
  public GameObjects(float xDimension, float yDimension) {
    
    this.player2CPU = false;
    this.ball = new GameObject("ball", (xDimension / 2), (yDimension / 2), 10, 60);
    this.player1 = new GameObject("player1", 50, (yDimension / 2), 50, 30);
    this.player2 = new GameObject("player2", (xDimension-50), (yDimension / 2), 50, 30);
    }
    public GameObjects(String gameType, float xDimension, float yDimension) {
      if(gameType.toLowerCase() == "1p" || gameType.toLowerCase() == "1 player") {
        this.player2CPU = true;
      }
      
      else {
        this.player2CPU = false;
      }
      
      this.ball = new GameObject("ball", (xDimension / 2), (yDimension / 2), 10, 60);
      this.player1 = new GameObject("player1", 50, (yDimension / 2), 50, 30);
      this.player2 = new GameObject("player2", (xDimension-50), (yDimension / 2), 50, 30);
      }
    
   public void setPaddleWidth(float width, int player) {
     if(player == 1) {
       this.player1.size = width;
     }
     if(player == 2) {
       this.player2.size = width;
     }
   }
   
   public void setBallSize(float size) {
     this.ball.size = size;
   }

}
