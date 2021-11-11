package game;

public class Paddle extends GameObject {
  
  public float speed;
  public int score;

  public Paddle(String name, float x, float y, float size, float speed) {
    super(name, x, y, size);
    this.speed = speed;
    this.score = 0;
    // TODO Auto-generated constructor stub
    
  }
  

}
