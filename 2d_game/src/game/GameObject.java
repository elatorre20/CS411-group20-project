package game;

/**
 * Generic game object class
 * <p>
 * @author Group 20 
 * @since 0.1.0
 */

public class GameObject {
  
  public String name = "";
  public float x = 0;
  public float y = 0;
  public float size = 0;

  public GameObject(String name, float x, float y, float size) {
    this.name = name;
    this.x = x;
    this.y = y;
    this.size = size;
  }

}
