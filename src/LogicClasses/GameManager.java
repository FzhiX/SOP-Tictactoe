package LogicClasses;

import ScreenClasses.Screen;
import ScreenClasses.ScreenManager;
import ScreenClasses.Screens.GameScreen;
import ScreenClasses.Screens.SettingsScreen;
import processing.core.*;

public class GameManager {

  ScreenManager screenManager;
  PApplet p;
  Cell[][] cells;

  public char winner = ' ';

  public GameManager(ScreenManager screenManagerIn) {
    this.screenManager = screenManagerIn;
    this.p = screenManagerIn.getP();

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
    if (winner != ' ') { // If there is a winner, don't update
      return;
    }
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

    winner = checkWin(cells);
    if (checkTie() == true) {
      winner = 'T';
    }
  }

  public char checkWin(Cell[][] cells) {
    int n = GameSettings.n;

    char tempChar = ' ';

    // Check rows
    for (int y = 0; y < n; y++) {
      tempChar = cells[0][y].getState();
      if (tempChar != ' ') {
        for (int x = 0; x < n; x++) {
          if (cells[x][y].getState() != tempChar) {
            break;
          } else if (x == n - 1) {
            return tempChar;
          }
        }
      }
    }

    // Check columns
    for (int x = 0; x < n; x++) {
      tempChar = cells[x][0].getState();
      if (tempChar != ' ') {
        for (int y = 0; y < n; y++) {
          if (cells[x][y].getState() != tempChar) {
            break;
          } else if (y == n - 1) {
            return tempChar;
          }
        }
      }
    }

    // Check diagonals
    tempChar = cells[0][0].getState();
    if (tempChar != ' ') {
      for (int i = 0; i < n; i++) {
        if (cells[i][i].getState() != tempChar) {
          break;
        } else if (i == n - 1) {
          return tempChar;
        }
      }
    }

    tempChar = cells[n - 1][0].getState();
    if (tempChar != ' ') {
      for (int i = 0; i < n; i++) {
        if (cells[n - 1 - i][i].getState() != tempChar) {
          break;
        } else if (i == n - 1) {
          return tempChar;
        }
      }
    }

    return ' ';
  }

  public boolean checkTie() {
    int n = GameSettings.n;

    for (int y = 0; y < n; y++) {
      for (int x = 0; x < n; x++) {
        if (cells[x][y].getState() == ' ') {
          return false;
        }
      }
    }

    return true;
  }
}
