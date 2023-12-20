package ScreenClasses.Objects;

import processing.core.PApplet;
import processing.core.PConstants;

public class Button {

  PApplet p;
  public int x, y, w, h, color;
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
    // Check if the given coordinates (xIn, yIn) are within the button's boundaries
    if (
      xIn >= x - w * 0.5 &&
      xIn <= x + w * 0.5 &&
      yIn >= y - h * 0.5 &&
      yIn <= y + h * 0.5
    ) {
      color = 230; // Change the button color to indicate it is being hovered over
      return true;
    } else {
      color = 255; // Reset the button color
      return false;
    }
  }

  public void update() {
    pressed2 = !pressed1;

    if (contains(p.mouseX, p.mouseY) && p.mousePressed) {
      pressed1 = true;
      color = 100; // Change the button color to indicate it is being pressed
    } else {
      pressed1 = false;
    }

    if (pressed1 && pressed2) {
      isPressed = true; // Set the button's pressed state to true
    } else {
      isPressed = false; // Set the button's pressed state to false
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
