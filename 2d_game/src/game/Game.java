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
import java.util.Scanner;
import java.util.TimerTask;
import java.util.Timer;

public class Game extends BasicGame{
  
  public static int windowWidth;
  public static int windowHeight; 
  public static double timeMs = 0;
  public static boolean onePlayer;
  public static float difficulty;
  public static int winScore;
  public static String player1Name;
  public static String player2Name;
  public static PaymentProcessor paymentProcessor = new PaymentProcessor();
  public static Scanner shell = new Scanner(System.in);
  public static AppGameContainer window;
  public static Timer timer = new Timer();
  public GameObjects gameObjects;
  public Ball ball;
  public Paddle player1; //left player
  public Paddle player2; //right player
  public AI ai;
  public Input input;
  
  public Game(String title) {
    super(title);
    this.gameObjects = new GameObjects(windowWidth, windowHeight, Game.difficulty, Game.onePlayer);
    this.ball = this.gameObjects.ball;
    this.player1 = this.gameObjects.player1;
    this.player2 = this.gameObjects.player2;
    this.ai = new AI(this);
    
  }
  
  public void init(GameContainer window) throws SlickException {
    
  }
  
  public void update(GameContainer window, int delta) throws SlickException {
    timeMs = timeMs + delta;
    this.input = window.getInput(); //process input for players
    if(input.isKeyDown(Input.KEY_W)) {
      this.player1.y = Math.max(this.player1.y - player1.speed, 0 + player1.size/(float)2);
    }
    else if(input.isKeyDown(Input.KEY_S)) {
      this.player1.y = Math.min(this.player1.y + player1.speed, Game.windowHeight - player1.size/(float)2);
    }
    if(this.gameObjects.player2CPU == true) { //if player 2 is controller by AI, allow AI to process move
      this.ai.calculateMove();
    }
    else { //otherwise process input for player 2
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
      if(player2.score == Game.winScore) {
        Game.window.pause();
        Game.timer.schedule(new Shutdown(), (long)1000);
        System.out.println(Game.player2Name + " wins!");
        while(true) {
          String exitHelper = "";
          exitHelper = Game.shell.nextLine();
          if(exitHelper.equals("") == false) {
            System.exit(0);
          }
        }
      }
    }
    if(temp == -1){
      player1.score++;
      if(player1.score == Game.winScore) {
        Game.window.pause();
        Game.timer.schedule(new Shutdown(), (long)1000);
        System.out.println(Game.player1Name + " wins!");
        while(true) {
          String exitHelper = "";
          exitHelper = Game.shell.nextLine();
          if(exitHelper.equals("") == false) {
            System.exit(0);
          }
        }
      }
    }
  }
  
  public void render(GameContainer window, Graphics g) throws SlickException {
    g.fillRect((this.ball.x - (this.ball.size /(float)2)), (this.ball.y - (this.ball.size /(float)2)), this.ball.size, this.ball.size);
    g.fillRect(this.player1.x-5, (this.player1.y - (this.player1.size/2)), 5, this.player1.size);
    g.fillRect(this.player2.x, (this.player2.y - (this.player2.size/2)), 5, this.player2.size);
    Score.drawNet(window, g, 50, 10);
    Score.drawString(window, g, Integer.toString(player1.score), 100, (Game.windowHeight/16) + 50);
    Score.drawString(window, g, Integer.toString(player2.score), windowWidth-210, (Game.windowHeight/16) +50);
  }
  
  /**
   * 
   * @param args the commandline arguments
   * 
   * parses the program arguments and sets appropriate values accordingly
   * 
   */
  public static void parseArgs(String[] args) {
//    Game.onePlayer = true; //default to one-player game
    for(int i = 0; i < args.length; i++) {
      String s = args[i];
//      if(s.length() > 2) {
//        if(s.substring(0, 3).toLowerCase().equals("-2p")){ //check for 1p game
//          Game.onePlayer = false;
//        }
//      }
      if(s.length() > 1) {
//        if(s.substring(0, 2).toLowerCase().equals("-d")) { //check for difficulty setting
//          Game.difficulty = Float.parseFloat(args[i+1]);
//        }
        if(s.substring(0, 2).toLowerCase().equals("-h")) { //check for 
          Game.windowHeight = Integer.parseInt(args[i+1]);
        }
        if(s.substring(0, 2).toLowerCase().equals("-w")) {
          Game.windowWidth = Integer.parseInt(args[i+1]);
        }
      }
      
    }//set default values for options not entered
//    if(Game.difficulty == 0.0) { //default to 5
//      Game.difficulty = (float) 5.0;
//    }
    if(Game.windowHeight == 0) { //default to 600
      Game.windowHeight = 600;
    }
    if(Game.windowWidth == 0) { //default to 800 (4:3 aspect ratio just like the ancient TVs pong was made for)
      Game.windowWidth = 800;
    }
    System.out.println("Enter difficulty (recommended: 6): ");
    String dif = Game.shell.nextLine();
    Float diff = Float.parseFloat(dif);
    Game.difficulty = diff;
    System.out.println("Enter winning score (recommended 15): ");
    String wscore = Game.shell.nextLine();
    int winscore = Integer.parseInt(wscore);
    Game.winScore = winscore;
  }
  
  /**
   * 
   * @param args options: -1p for a 1-player vs computer game
   */
  public static void main(String args[]) throws SlickException {
//    System.out.println(System.getProperty("java.library.path"));
    Game.parseArgs(args);
    AppGameContainer app = new AppGameContainer(new Game("Pong"));
    Game.window = app;
    app.setForceExit(false);
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
      boolean miss = (Math.random()*this.game.gameObjects.difficulty <= 2);
      if(miss) { //if the AI doesnt occasionally not move it would play perfectly and not be beatable
        
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
  
  private class Shutdown extends TimerTask{
    
    private Shutdown() {
    }
    @Override
    public void run() {
      Game.window.exit();
    }
    
  }

}
