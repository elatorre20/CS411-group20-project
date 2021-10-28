package game;

public class Ball extends GameObject {
  
  public float xSpeed = 0;
  public float ySpeed = 0;

  public Ball(String name, float x, float y, float size, float speed) {
    super(name, x, y, size);
    this.xSpeed = speed;
    this.ySpeed = 0;
  }
  
  public void update(int screenWidth, int screenHeight) {
    int score;
    score = this.checkEdgeCollision(screenWidth, screenHeight);
  }
  
  public int checkEdgeCollision(int screenWidth, int screenHeight) {
    if(this.x <= -this.size) {
      System.out.println("1");
      return 1; //left-side player gains 1 point
    }
    if(this.x >= screenWidth+this.size) {
      System.out.println("-1");
      return -1; //right-side player gains 1 point
    }
    if(this.y <= 0 || this.y >= screenHeight) {
      this.ySpeed = -(this.ySpeed);
    }
    this.x += this.xSpeed;
    this.y += this.ySpeed;
    System.out.println('0');
    return 0;
  }

}
