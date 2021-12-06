package game;

public class Paddle extends GameObject {
  
  public float speed;
  public int score;
  public String name;
  public String playerName;

  public Paddle(String name, float x, float y, float size, float speed, String playername) {
    super(name, x, y, size);
    this.speed = speed;
    this.score = 0;
    this.playerName = playername;
    // TODO Auto-generated constructor stub
    
  }
  

}
