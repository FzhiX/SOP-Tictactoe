package LogicClasses;

public class Cell {

  private char state;
  private int x, y, w, h;

  public Cell(int xIn, int yIn, int wIn, int hIn) {
    state = ' ';
    x = xIn;
    y = yIn;
    w = wIn;
    h = hIn;
  }

  public void setState(char stateIn) {
    state = stateIn;
  }

  public char getState() {
    return state;
  }

  public int getX() {
    return x;
  }

  public int getY() {
    return y;
  }

  public int getW() {
    return w;
  }

  public int getH() {
    return h;
  }

  public boolean contains(int xIn, int yIn) {
    if (xIn >= x && xIn <= x + w && yIn >= y && yIn <= y + h) {
      return true;
    }
    else {
      return false;
    }
  }
}
