package game;

import java.lang.Math;

/**
 * The ball
 * <p>
 * @author Group 20 
 * @since 0.1.1
 */

public class Ball extends GameObject {
  
  public float xSpeed = 0;
  public float ySpeed = 0;

  /**
   * 
   * @param name Same as super
   * @param x Same as super
   * @param y Same as super
   * @param size Same as super
   * @param speed The speed of the ball, higher value will make the game more difficult
   */
  public Ball(String name, float x, float y, float size, float speed) {
    super(name, x, y, size);
    this.xSpeed = speed;
    this.ySpeed = 0;
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
   * hits the left or right side of the screen
   * @param screenWidth
   * @param screenHeight
   * @return
   */
  public int checkEdgeCollision(int screenWidth, int screenHeight) {
    if(this.x <= -this.size) {
      this.x = screenWidth/(float)2; //placeholder until we sort out scoring behavior
      return 1; //left-side player gains 1 point
    }
    if(this.x >= screenWidth+this.size) {
      this.x = screenWidth/(float)2; //placeholder until we sort out scoring behavior
      return -1; //right-side player gains 1 point
    }
    if(this.y <= 0 || this.y >= screenHeight) {
      this.ySpeed = -(this.ySpeed);
    }
    return 0;
  }
  
  public void checkPaddleCollision(GameObject player1, GameObject player2) {
    GameObject[] paddles = {player1, player2};
    for(GameObject i : paddles) {
      if(this.x == i.x) {
        if(this.y >= (i.y-((float)i.size/2)) && this.y <= (i.y+((float)i.size/2))) {
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

}
