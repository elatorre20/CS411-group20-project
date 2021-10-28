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
import org.newdawn.slick.SlickException;

public class Game extends BasicGame{
  
  public static int windowWidth = 800;
  public static int windowHeight = 800; 
  public GameObjects gameObjects = new GameObjects(windowWidth, windowHeight);
  public Ball ball = this.gameObjects.ball;
  public GameObject player1 = this.gameObjects.player1;
  public GameObject player2 = this.gameObjects.player2;
  public static double timeMs = 0;
  
  public Game(String title) {
    super(title);
  }
  
  public void init(GameContainer window) throws SlickException {
    
  }
  
  public void update(GameContainer window, int delta) throws SlickException {
    timeMs = timeMs + delta;
    this.ball.update(windowWidth, windowHeight, player1, player2);
  }
  
  public void render(GameContainer window, Graphics g) throws SlickException {
    g.fillRect((this.ball.x - (this.ball.size /2)), (this.ball.y - (this.ball.size /2)), this.ball.size, this.ball.size);
    g.fillRect(this.player1.x, (this.player1.y - (this.player1.size/2)), 5, this.player1.size);
    g.fillRect(this.player2.x, (this.player2.y - (this.player2.size/2)), 5, this.player2.size);
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
