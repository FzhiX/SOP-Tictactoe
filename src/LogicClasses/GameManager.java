package LogicClasses;

import processing.core.*;

public class GameManager {

  PApplet p;
  Cell[][] cells;

  public GameManager(PApplet pIn) {
    p = pIn;
    init();
  }

  private void init() {
    int n = GameSettings.n;
    cells = new Cell[n][n];

    int tempWidth = p.width / n;
    int tempHeight = p.height / n;

    for (int y = 0; y < n; y++) {
      for (int x = 0; x < n; x++) {
        cells[x][y] =
          new Cell((x * tempWidth), (y * tempHeight), tempWidth, tempHeight);
      }
    }
  }

  public Cell getCell(int x, int y) {
    return cells[x][y];
  }

  public void update() {
    /*
    if (p.keyPressed && p.key == 'a') {
      GameSettings.n++;
      init();
    }
    */

    int n = GameSettings.n;

    if (p.mousePressed) {
      for (int y = 0; y < n; y++) {
        for (int x = 0; x < n; x++) {
          Cell tempCell = cells[x][y];
          if (
            tempCell.contains(p.mouseX, p.mouseY) && tempCell.getState() == ' '
          ) {
            tempCell.setState('X');
          }
        }
      }
    }
  }
}
