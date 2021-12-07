package game;

import java.lang.Math;
import java.util.Timer;
import java.util.TimerTask;

/**
 * The ball
 * <p>
 * @author Group 20 
 * @since 0.1.1
 */

public class Ball extends GameObject {
  
  public float xSpeed = 0;
  public float ySpeed = 0;
  public int screenWidth;
  public int screenHeight;
  public Timer timer;
  public float speed;
  public float dir;

  /**
   * 
   * @param name Same as super
   * @param x Same as super
   * @param y Same as super
   * @param size Same as super
   * @param speed The speed of the ball, higher value will make the game more difficult
   */
  public Ball(String name, float x, float y, float size, float speed, int screenWidth, int screenHeight) {
    super(name, x, y, size);
    this.xSpeed = speed;
    this.ySpeed = 0;
    this.speed = speed;
    this.dir = speed;
    this.screenHeight = screenHeight;
    this.screenWidth = screenWidth;
    this.timer = new Timer();
  }
  
  /**
   * Update the behavior of the ball
   * <p>
   * Checks for collisions and scores then moves the ball appropriately
   * @param screenWidth
   * @param screenHeight
   */
  public int update(int screenWidth, int screenHeight, GameObject player1, GameObject player2) {
	this.checkPaddleCollision(player1, player2);
    int score;
    score = this.checkEdgeCollision(screenWidth, screenHeight);
    if(score != 0) {
      return score;
    }
    else {
      this.x += this.xSpeed;
      this.y += this.ySpeed;
      return 0;
    }
  }
  
  /**
   * Checks for collisions with the edge of the screen
   * <p>
   * Bounces the ball off the top and bottom edges, records a score if the ball 
   * hits the left or right side of the screen, then sends the ball back to the middle for a reset
   * @param screenWidth
   * @param screenHeight
   * @return
   */
  public int checkEdgeCollision(int screenWidth, int screenHeight) {
    if(this.x <= -this.size) {
      this.x = (this.screenWidth/(float)2);
      this.y = (this.screenHeight/(float)2);
      this.ySpeed = 0;
      this.xSpeed = 0;
      this.dir = -this.speed;
      this.timer.schedule(new Reset(this), (long)1000);
      return 1; //right-side player gains 1 point
    }
    if(this.x >= screenWidth+this.size) {
      this.x = (this.screenWidth/(float)2);
      this.y = (this.screenHeight/(float)2);
      this.ySpeed = 0;
      this.xSpeed = 0;
      this.dir = this.speed;
      this.timer.schedule(new Reset(this), (long)1000);
      return -1; //left-side player gains 1 point
    }
    if(this.y <= 0 || this.y >= screenHeight) {
      this.ySpeed = -(this.ySpeed);
    }
    return 0;
  }
  
  /**
   * Checks for collisions with the paddles
   * <p>
   * Bounces the ball off the top and bottom edges, records a score if the ball 
   * hits the left or right side of the screen
   * @param left player
   * @param right player
   * @return
   */
  public void checkPaddleCollision(GameObject player1, GameObject player2) {
    GameObject[] paddles = {player1, player2};
    for(GameObject i : paddles) {
      if((int)this.x < ((int)i.x+this.speed) && (int)this.x > ((int)i.x-this.speed)) {
        if(this.y >= (i.y-((float)i.size/2)) && this.y <= (i.y+((float)i.size/2))) {
          if(this.xSpeed < 0) {
            this.xSpeed--;
          }
          if(this.xSpeed > 0) {
            this.xSpeed++;
          }
        	this.xSpeed = -this.xSpeed;
        	float z = (float)Math.random();
        	if(z <= 0.5) {
        	  z = (float)Math.random() * 5;
        	  this.ySpeed += z;
        	  
        	}	
        }
      }
    }
  }
  
  /**
   * 
   * @author Group 20 
   * @since 0.1.7
   *
   */
  private class Reset extends TimerTask{
    
    private Ball ball;
    private Reset(Ball ball) {
      this.ball = ball;
    }
    @Override
    public void run() {
      this.ball.ySpeed = 0;
      this.ball.xSpeed = this.ball.dir;
    }
    
  }

}
