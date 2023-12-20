package ScreenClasses.Screens;

import LogicClasses.GameSettings;
import ScreenClasses.*;
import ScreenClasses.Objects.Button;
import processing.core.*;

public class SettingsScreen extends Screen {

  PApplet p;
  ScreenManager screenManager;
  Button addN, subN, AIstart;

  public SettingsScreen(ScreenManager screenManagerIn) {
    this.screenManager = screenManagerIn;
    this.p = screenManagerIn.getP();

    // Create the "addN" button
    addN = new Button(
        p,
        (p.width / 5),
        (p.height / 3) * 2,
        p.width / 8,
        p.height / 10);

    // Create the "subN" button
    subN = new Button(
        p,
        p.width - (p.width / 5),
        (p.height / 3) * 2,
        p.width / 8,
        p.height / 10);

    // Create the "AIstart" button
    AIstart = new Button(
        p,
        p.width / 2,
        p.height / 2,
        p.width / 8,
        p.height / 10);
  }

  public void render() {
    // Render the "addN" button
    addN.render();

    // Render the "subN" button
    subN.render();

    // Render the "AIstart" button
    AIstart.render();

    p.textSize(32); // Set the text size
    p.textAlign(PConstants.CENTER); // Set the text alignment to center
    p.fill(50);
    p.text("Current board size: " + GameSettings.n + " * " + GameSettings.n, p.width / 2, p.height / 10); // Display the text

    p.text(GameSettings.turn + " starts", p.width / 2, p.height / 10 * 2); 

    p.textSize(16); 
    p.fill(0);
    p.text("Increase board size", addN.x + 10, addN.y - (10 + addN.h / 2)); 
    p.text("Decrease board size", subN.x + 10, subN.y - (10 + addN.h / 2));

    p.textSize(16); 
    p.fill(50);
    p.text("Let the AI start?", AIstart.x, AIstart.y - (10 + AIstart.h / 2)); 

    p.textSize(32);
    p.fill(50);
    p.text("Press Enter to start the game", p.width / 2, p.height / 10 * 9);

  }


  public void update() {
    // Update the "addN" button
    addN.update();

    // Update the "subN" button
    subN.update();

    // Update the "AIstart" button
    AIstart.update();

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

    // Let the AI start the game if "AIstart" button is pressed
    if (GameSettings.turn == 'h') {
      if (AIstart.isPressed == true) {
        GameSettings.turn = 'c';
      }
    } else if (GameSettings.turn == 'c') {
      if (AIstart.isPressed == true) {
        GameSettings.turn = 'h';
      }
    }

    // Switch to the GameScreen when the Enter key is pressed
    if (p.keyPressed && p.keyCode == PConstants.ENTER) {
      screenManager.setCurrentScreen(new GameScreen(screenManager));
    }
  }
}
