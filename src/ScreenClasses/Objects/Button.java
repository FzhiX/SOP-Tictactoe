package ScreenClasses.Objects;

import processing.core.PApplet;
import processing.core.PConstants;

public class Button {

  PApplet p;
  int x, y, w, h;
  boolean isPressed;
  boolean Pressed1, tempPressed2;

  public Button(PApplet pIn, int xIn, int yIn, int wIn, int hIn) {
    p = pIn;
    x = xIn;
    y = yIn;
    w = wIn;
    h = hIn;
    isPressed = false;
  }

  public void render() {
    p.fill(255);
    p.stroke(255, 229, 57);
    p.strokeWeight(3);

    p.rectMode(PConstants.CORNER);
    p.rect(x, y, w, h);
  }

  public boolean contains(int xIn, int yIn) {
    if (xIn >= x && xIn <= x + w && yIn >= y && yIn <= y + h) {
      return true;
    } else {
      return false;
    }
  }

  public void update() {
    if (p.mousePressed) {
      if (contains(p.mouseX, p.mouseY) == true) {
        isPressed = true;
      }
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
