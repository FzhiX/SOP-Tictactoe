package LogicClasses;

public class GameSettings {

  public static int n = 3; // The size of the board (n*n) - "static" means that the variable is shared between all instances of the class (global variable)
  public static char turn = 'h'; // 'h'(player 1) or 'c'(computer or player 2)
  public static int color = 238; // The background color of the application window

  private GameSettings() {} // Private constructor to prevent instantiation
}
