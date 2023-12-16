package ScreenClasses.Screens;

import LogicClasses.GameSettings;
import ScreenClasses.*;
import ScreenClasses.Objects.Button;
import processing.core.*;

public class SettingsScreen extends Screen {

  PApplet p;
  ScreenManager screenManager;
  Button addN, subN;

  public SettingsScreen(ScreenManager screenManagerIn) {
    this.screenManager = screenManagerIn;
    this.p = screenManagerIn.getP();

    // Create the "addN" button
    addN =
      new Button(
        p,
        (p.width / 5),
        (p.height / 3) * 2,
        p.width / 8,
        p.height / 10
      );

    // Create the "subN" button
    subN =
      new Button(
        p,
        p.width - (p.width / 5),
        (p.height / 3) * 2,
        p.width / 8,
        p.height / 10
      );
  }

  public void render() {
    // Render the "addN" button
    addN.render();

    // Render the "subN" button
    subN.render();
  }

  public void update() {
    // Update the "addN" button
    addN.update();

    // Update the "subN" button
    subN.update();

    // Increase the value of GameSettings.n if "addN" button is pressed
    if (addN.isPressed == true) {
      if (GameSettings.n < 4) {
        GameSettings.n++;
      } else {
        System.out.println("Can't go higher than 4");
      }
    }

    // Decrease the value of GameSettings.n if "subN" button is pressed
    if (subN.isPressed == true) {
      if (GameSettings.n > 1) {
        GameSettings.n--;
      } else {
        System.out.println("Can't go lower than 1");
      }
    }

    // Switch to the GameScreen when the Enter key is pressed
    if (p.keyPressed && p.keyCode == PConstants.ENTER) {
      screenManager.setCurrentScreen(new GameScreen(screenManager));
    }
  }
}
