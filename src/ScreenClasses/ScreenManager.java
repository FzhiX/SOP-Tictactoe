package ScreenClasses;

import ScreenClasses.Screens.*;
import processing.core.*;

public class ScreenManager {

  PApplet p; // Reference to the main PApplet object
  Screen currentScreen; // Reference to the current screen object

  public ScreenManager(PApplet pIn) {
    p = pIn;
    currentScreen = new SettingsScreen(this); // Initialize the current screen with the SettingsScreen
  }

  public void run() {
    currentScreen.update(); // Update the current screen
    currentScreen.render(); // Render the current screen
  }

  public void setCurrentScreen(Screen screenIn) {
    currentScreen = screenIn; // Set the current screen to the provided screen
  }

  public PApplet getP() {
    return p; // Return the main PApplet object
  }
}
