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
  public static double timeMs = 0;
  public static boolean onePlayer;
  public GameObjects gameObjects;
  public Ball ball;
  public Paddle player1; //left player
  public Paddle player2; //right player
  public AI ai;
  public Input input;
  
  public Game(String title) {
    super(title);
    this.gameObjects = new GameObjects(windowWidth, windowHeight, 5, Game.onePlayer);
    this.ball = this.gameObjects.ball;
    this.player1 = this.gameObjects.player1;
    this.player2 = this.gameObjects.player2;
    this.ai = new AI(this);
    
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
    if(this.gameObjects.player2CPU == true) {
      this.ai.calculateMove();
    }
    else {
      if(input.isKeyDown(Input.KEY_UP)) {
        this.player2.y = Math.max(this.player2.y - player2.speed, 0 + player1.size/(float)2);
      }
      else if(input.isKeyDown(Input.KEY_DOWN)) {
        this.player2.y = Math.min(this.player2.y + player2.speed, Game.windowHeight - player1.size/(float)2);
      }
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
    Score.drawNet(window, g, 50, 10);
    Score.drawString(window, g, Integer.toString(player1.score), 100, 100);
    Score.drawString(window, g, Integer.toString(player2.score), windowWidth-210, 100);
  }
  
  /**
   * 
   * @param args options: -1p for a 1-player vs computer game
   */
  public static void main(String args[]) throws SlickException {
    System.out.println("lorem ipsum dolor sit amet");
    if(args[0].substring(0, 3).toLowerCase().equals("-1p")) {
      Game.onePlayer = true;
    }
    else {
      Game.onePlayer = false;
    }
    AppGameContainer app = new AppGameContainer(new Game("Pong"));
    app.setDisplayMode(windowWidth, windowHeight, false);
    app.setShowFPS(false);
    app.setTargetFrameRate(60);
    app.start();

  }
  
  /**
   * 
   * @author Group 20
   * @since 0.1.10
   * 
   * The AI for the computer player. Only used in a 1-player game.
   *
   */
  private class AI {
    
    private Game game;
    private Paddle player;
    private Ball ball;
    
    /**
     * 
     * @param game the game logic for the pong game
     */
    public AI(Game game) {
      this.game = game;
      this.player = this.game.gameObjects.player2;
      this.ball = this.game.gameObjects.ball;
    }
    
    /**
     * calculates the optimal move given the current position of the ball
     */
    public void calculateMove() {
      float target = this.ball.y;
      boolean miss = (Math.random()*this.game.gameObjects.difficulty <= 1);
      if(miss) {
        
      }
      else if(this.player.y < ball.y + (this.player.size/(float)2) - this.ball.size && this.player.y > ball.y - (this.player.size/(float)2) + this.ball.size) {
        
      }
      else if(this.player.y > target) {
        this.player.y = Math.max(this.player.y - this.player.speed, 0 + this.player.size/(float)2);
      }
      else if(this.player.y < target) {
        this.player.y = Math.min(this.player.y + this.player.speed, Game.windowHeight - this.player.size/(float)2);
      }
    }
    
    
    
  }

}
