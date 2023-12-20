package LogicClasses;

import ScreenClasses.*;
import ScreenClasses.Screens.*;
import processing.core.*;

public class GameManager {

  ScreenManager screenManager;
  PApplet p;
  Cell[][] cells;
  byte results[] = new byte[43046721]; // dynamic programming with 3^(4^2) possible states

  public char winner = ' ';

  public GameManager(ScreenManager screenManagerIn) {
    this.screenManager = screenManagerIn;
    this.p = screenManagerIn.getP();
    for (int i = 0; i < Math.pow(3, 16); i++) {
      results[i] = 2;
    }

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
              System.out.println("Score for move (" + x + "," + y + "): " + tempScore); // print the score
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
        System.out.println("");
        GameSettings.turn = 'h';
        break;
      default: // If turn is not 'h' or 'c', go to settings screen
        screenManager.setCurrentScreen(new SettingsScreen(screenManager));
        break;
    }

    winner = checkWin(cells);
  }

  public char checkWin(Cell[][] cellsIn) {
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

    // Check tie
    if (checkTie(cellsIn)) {
      return 'T';
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

    int gameState = generateIndex();
    if (results[gameState] != 2) {
      return results[gameState];
    }

    char result = checkWin(cells);
    if (result != ' ') {
      switch (result) {
        case 'X':
          results[gameState] = -1;
          return -1;
        case 'O':
          results[gameState] = 1;
          return 1;
        case 'T':
          results[gameState] = 0;
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
            bestScore = Math.max(tempScore, bestScore);
          }
        }
      }
      results[gameState] = (byte) bestScore;
      return bestScore;
    } else {
      int bestScore = Integer.MAX_VALUE; // "infinity"
      for (int y = 0; y < n; y++) {
        for (int x = 0; x < n; x++) {
          Cell tempCell = cellsIn[x][y];
          if (tempCell.getState() == ' ') {
            tempCell.setState('X');
            int tempScore = minimax(cellsIn, depthIn + 1, true);
            tempCell.setState(' ');
            bestScore = Math.min(tempScore, bestScore);
          }
        }
      }
      results[gameState] = (byte) bestScore;
      return bestScore;
    }
  }

  int generateIndex() { // Generates an index for the results array
    int index = 0;
    int n = GameSettings.n;
    int currentDigit = 0;

    for (int y = 0; y < n; y++) {
      for (int x = 0; x < n; x++) {
        char state = cells[x][y].getState();
        if (state == 'X') {
          index += 1 * Math.pow(3, currentDigit);
        } else if (state == 'O') {
          index += 2 * Math.pow(3, currentDigit);
        }
        currentDigit++;
      }
    }

    return index;
  }
}
