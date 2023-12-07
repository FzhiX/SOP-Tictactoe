import LogicClasses.GameSettings;
import ScreenClasses.ScreenManager;
import processing.core.*;

public class App extends PApplet {

  ScreenManager screenManager;

  public static void main(String[] args) {
    PApplet.main("App");
  }

  public void setup() {
    frameRate(24);
  }

  public void settings() {
    size(600, 600, P2D);
    this.screenManager = new ScreenManager(this);
  }

  public void draw() {
    background(238);
    screenManager.run();
    System.out.println(GameSettings.n);
  }
}
