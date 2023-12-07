package ScreenClasses.Screens;

import LogicClasses.*;
import ScreenClasses.*;
import processing.core.*;

public class GameScreen extends Screen {

  ScreenManager screenManager;
  PApplet p;
  GameManager gameManager;

  public GameScreen(ScreenManager screenManagerIn) {
    this.screenManager = screenManagerIn;
    this.p = screenManagerIn.getP();
    gameManager = new GameManager(p);
  }

  public void update() {
    gameManager.update();
  }

  public void render() {
    renderCells();
  }

  private void renderCells() {
    p.rectMode(PConstants.CORNER);
    p.stroke(0);
    p.strokeWeight(5);
    int n = GameSettings.n;
    for (int y = 0; y < n; y++) {
      for (int x = 0; x < n; x++) {
        Cell tempCell = gameManager.getCell(x, y);
        p.fill(255);
        p.rect(
          tempCell.getX(),
          tempCell.getY(),
          tempCell.getW(),
          tempCell.getH()
        );

        p.textAlign(PConstants.CENTER, PConstants.CENTER);
        p.fill(0);

        p.text(
          tempCell.getState(),
          (int) (tempCell.getX() + tempCell.getW() * 0.5),
          (int) (tempCell.getY() + tempCell.getH() * 0.5)
        );
      }
    }
  }
}
