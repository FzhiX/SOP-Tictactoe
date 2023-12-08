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

    switch (GameSettings.turn) {
      case 'h':
        if (p.mousePressed) {
          for (int y = 0; y < n; y++) {
            for (int x = 0; x < n; x++) {
              Cell tempCell = cells[x][y];
              if (
                tempCell.contains(p.mouseX, p.mouseY) &&
                tempCell.getState() == ' '
              ) {
                tempCell.setState('X');

                GameSettings.turn = 'c';
              }
            }
          }
        }
        break;
      case 'c':
        int bestScore = Integer.MIN_VALUE; // "negative infinity"
        int[] bestMove = { 0, 0 };

        for (int y = 0; y < n; y++) {
          for (int x = 0; x < n; x++) {
            Cell tempCell = cells[x][y];
            if (tempCell.getState() == ' ') {
              tempCell.setState('O');
              int tempScore = minimax(cells, 0, false);
              tempCell.setState(' ');
              if (tempScore > bestScore) {
                bestScore = tempScore;
                bestMove[0] = x;
                bestMove[1] = y;
              }
            }
          }
        }
        cells[bestMove[0]][bestMove[1]].setState('O');
        GameSettings.turn = 'h';
        break;
      default: // If turn is not 'h' or 'c', go to settings screen
        screenManager.setCurrentScreen(new SettingsScreen(screenManager));
        break;
    }

    winner = checkWin(cells);
  }

  public char checkWin(Cell[][] cellsIn) {
    if (checkTie(cellsIn)) {
      return 'T';
    }

    int n = GameSettings.n;

    char tempChar = ' ';

    // Check rows
    for (int y = 0; y < n; y++) {
      tempChar = cellsIn[0][y].getState();
      if (tempChar != ' ') {
        for (int x = 0; x < n; x++) {
          if (cellsIn[x][y].getState() != tempChar) {
            break;
          } else if (x == n - 1) {
            return tempChar;
          }
        }
      }
    }

    // Check columns
    for (int x = 0; x < n; x++) {
      tempChar = cellsIn[x][0].getState();
      if (tempChar != ' ') {
        for (int y = 0; y < n; y++) {
          if (cellsIn[x][y].getState() != tempChar) {
            break;
          } else if (y == n - 1) {
            return tempChar;
          }
        }
      }
    }

    // Check diagonals
    tempChar = cellsIn[0][0].getState();
    if (tempChar != ' ') {
      for (int i = 0; i < n; i++) {
        if (cellsIn[i][i].getState() != tempChar) {
          break;
        } else if (i == n - 1) {
          return tempChar;
        }
      }
    }

    tempChar = cellsIn[n - 1][0].getState();
    if (tempChar != ' ') {
      for (int i = 0; i < n; i++) {
        if (cellsIn[n - 1 - i][i].getState() != tempChar) {
          break;
        } else if (i == n - 1) {
          return tempChar;
        }
      }
    }

    return ' ';
  }

  public boolean checkTie(Cell[][] cellsIn) {
    int n = GameSettings.n;

    for (int y = 0; y < n; y++) {
      for (int x = 0; x < n; x++) {
        if (cellsIn[x][y].getState() == ' ') {
          return false;
        }
      }
    }
    return true;
  }

  public int minimax(Cell[][] cellsIn, int depthIn, boolean isMaximizing) {
    int n = GameSettings.n;

    char result = checkWin(cells);
    if (result != ' ') {
      switch (result) {
        case 'X':
          return -1;
        case 'O':
          return 1;
        case 'T':
          return 0;
      }
    }

    if (isMaximizing) {
      int bestScore = Integer.MIN_VALUE; // "negative infinity"
      for (int y = 0; y < n; y++) {
        for (int x = 0; x < n; x++) {
          Cell tempCell = cellsIn[x][y];
          if (tempCell.getState() == ' ') {
            tempCell.setState('O');
            int tempScore = minimax(cellsIn, depthIn + 1, false);
            tempCell.setState(' ');
            if (tempScore > bestScore) {
              bestScore = tempScore;
            }
          }
        }
      }
      return bestScore;
    } else {
      int bestScore = Integer.MAX_VALUE; // "negative infinity"
      for (int y = 0; y < n; y++) {
        for (int x = 0; x < n; x++) {
          Cell tempCell = cellsIn[x][y];
          if (tempCell.getState() == ' ') {
            tempCell.setState('X');
            int tempScore = minimax(cellsIn, depthIn + 1, true);
            tempCell.setState(' ');
            if (tempScore < bestScore) {
              bestScore = tempScore;
            }
          }
        }
      }
      return bestScore;
    }
  }
}
