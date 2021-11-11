package game;

/**
 * Primary game logic
 * <p>
 * @author Group 20 
 * @since 0.1.0
 */

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import java.lang.Math;

public class Game extends BasicGame{
  
  public static int windowWidth = 800;
  public static int windowHeight = 800; 
  public GameObjects gameObjects = new GameObjects(windowWidth, windowHeight, 5);
  public Ball ball = this.gameObjects.ball;
  public Paddle player1 = this.gameObjects.player1; //left player
  public Paddle player2 = this.gameObjects.player2; //right player
  public static double timeMs = 0;
  public Input input;
  
  public Game(String title) {
    super(title);
  }
  
  public void init(GameContainer window) throws SlickException {
    
  }
  
  public void update(GameContainer window, int delta) throws SlickException {
    timeMs = timeMs + delta;
    this.input = window.getInput();
    if(input.isKeyDown(Input.KEY_W)) {
      this.player1.y = Math.max(this.player1.y - player1.speed, 0 + player1.size/(float)2);
    }
    else if(input.isKeyDown(Input.KEY_S)) {
      this.player1.y = Math.min(this.player1.y + player1.speed, Game.windowHeight - player1.size/(float)2);
    }
    if(input.isKeyDown(Input.KEY_UP)) {
      this.player2.y = Math.max(this.player2.y - player2.speed, 0 + player1.size/(float)2);
    }
    else if(input.isKeyDown(Input.KEY_DOWN)) {
      this.player2.y = Math.min(this.player2.y + player2.speed, Game.windowHeight - player1.size/(float)2);
    }
    int temp;
    temp = this.ball.update(windowWidth, windowHeight, player1, player2);
    if(temp == 1) {
      player2.score++;
    }
    if(temp == -1){
      player1.score++;
    }
  }
  
  public void render(GameContainer window, Graphics g) throws SlickException {
    g.fillRect((this.ball.x - (this.ball.size /(float)2)), (this.ball.y - (this.ball.size /(float)2)), this.ball.size, this.ball.size);
    g.fillRect(this.player1.x-5, (this.player1.y - (this.player1.size/2)), 5, this.player1.size);
    g.fillRect(this.player2.x, (this.player2.y - (this.player2.size/2)), 5, this.player2.size);
    Score.drawString(window, g, Integer.toString(player1.score), 100, 100);
    Score.drawString(window, g, Integer.toString(player2.score), windowWidth-210, 100);
  }
  
  public static void main(String args[]) throws SlickException {
    System.out.println("lorem ipsum dolor sit amet");
    AppGameContainer app = new AppGameContainer(new Game("Pong"));
    app.setDisplayMode(windowWidth, windowHeight, false);
    app.setShowFPS(false);
    app.setTargetFrameRate(60);
    app.start();

  }

}
