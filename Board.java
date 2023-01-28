/**
 * An improved, simplified version of the board for
 * Connect Four. The major changes involve
 * the implementation of recursive thinking.
 * 
 * @author Abraham Martinez
 * @version 2.0
 * 
 */

public class Board {

  private char[][] board;

  public Board(boolean debug) {
    final int row = 6;
    final int col = 7;
    final char empty = '.';
    board = new char[row][col];
    generateBoard(empty, debug);
  }

  // Generate the initial board
  private void generateBoard(char Default, boolean testBoard) {
    for (int i = 0; i < board.length; i++) {
      for (int j = 0; j < board[0].length; j++) {
        board[i][j] = Default;
      }
    }
    if (testBoard) {
      System.out.println(displayBoard());
    }
  }

  // Place a checker on the board
  public boolean placeChecker(char c, int col) {
    int row = 0;
    if (validPlacement(0, col - 1)) {
      row = placeCheckerHelper(c, board.length - 1, col - 1);
    } else {
      throw new IllegalArgumentException("Unable to place checker :(");
    }
    System.out.println(displayBoard());
    return checkForVictory(c, row, col - 1);

  }

  // Place the checker according to open position in selected column.
  private int placeCheckerHelper(char c, int row, int col) {
    if (row >= 0) {
      if (board[row][col] == '.') {
        board[row][col] = c;
      } else {
        return placeCheckerHelper(c, row - 1, col);
      }
    }
    return row == -1 ? 0 : row; // FIX ME
  }

  // Check for victory based on checker placement.
  private boolean checkForVictory(char c, int row, int col) {
    final int[] rowDirection = { 1, 1, 0, -1, 1, -1, -1, 0 };
    final int[] colDirection = { 1, 0, 1, 1, -1, -1, 0, -1 };
    final int TOTAL_DIR = rowDirection.length;
    int streak = 1;
    for (int direction = 0; direction < TOTAL_DIR; direction++) {
      boolean vic = checkForVictoryHelper(c, row, col, streak, rowDirection, colDirection, direction);
      if (vic) {
        return true;
      }
    }
    return false;
  }

  // helper for checkForVictory (recursive)
  private boolean checkForVictoryHelper(char c, int row, int col, int streak, int[] rowDir, int[] colDir, int dir) {
    if (!validPlacement(row, col) || board[row][col] != c) {
      return false;
    } else if (streak == 4) {
      return true;
    }
    int currRow = row + rowDir[dir];
    int currCol = col + colDir[dir];
    return checkForVictoryHelper(c, currRow, currCol, streak + 1, rowDir, colDir, dir);
  }

  // Checks for inbounds compliance
  private boolean validPlacement(int row, int col) {
    return row < board.length && col < board[0].length && row >= 0 && col >= 0;
  }

  // Display the board on the terminal
  private String displayBoard() {
    String result = "";
    for (int i = 0; i < board.length; i++) {
      result += "[";
      for (int j = 0; j < board[0].length; j++) {
        if (j + 1 == board[0].length) {
          result += board[i][j];
          continue;
        }
        result += board[i][j] + " ";
      }
      result += "]\n";
    }
    return result;
  }
}
