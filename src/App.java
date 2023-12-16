import LogicClasses.GameSettings;
import ScreenClasses.ScreenManager;
import processing.core.*;

public class App extends PApplet {

  ScreenManager screenManager;

  public static void main(String[] args) {
    PApplet.main("App"); // Launches the application
  }

  public void setup() {
    frameRate(24); // Sets the frame rate of the application
  }

  public void settings() {
    size(600, 600, P2D); // Sets the size of the application window
    this.screenManager = new ScreenManager(this); // Initializes the screen manager
  }

  public void draw() {
    background(238); // Sets the background color of the application window
    screenManager.run(); // Runs the screen manager
    System.out.println(GameSettings.n); // Prints the value of GameSettings.n (for testing purposes)
  }
}
