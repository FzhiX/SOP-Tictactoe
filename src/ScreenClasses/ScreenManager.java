package ScreenClasses;

import ScreenClasses.Screens.*;
import processing.core.*;

public class ScreenManager {

  PApplet p;
  Screen currentScreen;

  public ScreenManager(PApplet pIn) {
    p = pIn;
    currentScreen = new SettingsScreen(this);
  }

  public void run() {
    currentScreen.update();
    currentScreen.render();
  }

  public void setCurrentScreen(Screen screenIn) {
    currentScreen = screenIn;
  }

  public PApplet getP() {
    return p;
  }
}
