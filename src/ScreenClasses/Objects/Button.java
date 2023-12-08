package ScreenClasses.Objects;

import LogicClasses.GameSettings;
import processing.core.PApplet;
import processing.core.PConstants;

public class Button {

  PApplet p;
  private int x, y, w, h, color;
  public boolean isPressed = false;
  private boolean pressed1 = false, pressed2 = true; // for helping with the mousePressed() method

  public Button(PApplet pIn, int xIn, int yIn, int wIn, int hIn) {
    p = pIn;
    x = xIn;
    y = yIn;
    w = wIn;
    h = hIn;
    color = 255;
  }

  public void render() {
    p.fill(color);
    p.stroke(255, 229, 57);
    p.strokeWeight(3);

    p.rectMode(PConstants.CENTER);
    p.rect(x, y, w, h);
  }

  public boolean contains(int xIn, int yIn) {
    if (
      xIn >= x - w * 0.5 &&
      xIn <= x + w * 0.5 &&
      yIn >= y - h * 0.5 &&
      yIn <= y + h * 0.5
    ) {
      color = 230;
      return true;
    } else {
      color = 255;
      return false;
    }
  }

  public void update() {
    pressed2 = !pressed1;

    if (contains(p.mouseX, p.mouseY) && p.mousePressed) {
      pressed1 = true;
      color = 100;
    } else {
      pressed1 = false;
    }

    if (pressed1 && pressed2) {
      isPressed = true;
    } else {
      isPressed = false;
    }
  }

  public boolean isPressed() {
    if (isPressed == true) {
      return true;
    } else {
      return false;
    }
  }
}
