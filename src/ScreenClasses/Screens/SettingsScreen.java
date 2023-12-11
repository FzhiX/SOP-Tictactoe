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

    addN =
      new Button(
        p,
        (p.width / 5),
        (p.height / 3) * 2,
        p.width / 8,
        p.height / 10
      );

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
    addN.render();
    subN.render();
  }

  public void update() {
    addN.update();
    subN.update();

    if (addN.isPressed == true) {
      if (GameSettings.n < 4) {
        GameSettings.n++;
      } else {
        System.out.println("Can't go higher than 4");
      }
    }
    if (subN.isPressed == true) {
      if (GameSettings.n > 1) {
        GameSettings.n--;
      } else {
        System.out.println("Can't go lower than 1");
      }
    }

    if (p.keyPressed && p.keyCode == PConstants.ENTER) {
      screenManager.setCurrentScreen(new GameScreen(screenManager));
    }
  }
}
