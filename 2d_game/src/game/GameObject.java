package game;

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
