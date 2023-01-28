import java.util.Scanner;

/**
 * An improved, simplified version of Connect Four.
 * The major changes involve the implementation of
 * recursive thinking.
 * 
 * @author Abraham Martinez
 * @version 2.0
 * 
 */

public class ConnectFour {

  private String user1;
  private String user2;

  public ConnectFour(boolean debug, Scanner console) {
    Board board;
    char playAgain = 'y';
    greetUsers(console);
    do {
      board = new Board(debug);
      // greetUsers(console);
      playAgain = playGame(board, console);
    } while (playAgain == 'y' || playAgain == 'Y');
  }

  // Greets players and collects their names.
  private void greetUsers(Scanner console) {
    System.out.println("Welcome to Connect Four!");
    System.out.print("Enter player 1 name: ");
    user1 = console.next();
    System.out.print("Enter player 2 name: ");
    user2 = console.next();
    System.out.println("\nWelcome " + user1 + " and " + user2 + "!\n");
    System.out.println(user1 + " will be Red and " + user2 + " will be Blue!\n");
  }

  // Plays a game of Connect Four
  private char playGame(Board board, Scanner console) {
    short playerTurn = 0;
    char c = '\0';
    int col;
    boolean finished = false;
    boolean victory = false;
    while (!finished) {
      c = (playerTurn % 2 == 0) ? 'r' : 'b';
      String turn = (c == 'r') ? user1 : user2;
      System.out.print(turn + ", pick a column: ");
      col = console.nextInt();
      victory = board.placeChecker(c, col);
      if (victory) {
        break;
      }
      playerTurn++;
    }
    if (victory) {
      String winner = (playerTurn % 2 == 0) ? user1 : user2;
      System.out.println("Les go " + winner + "!!! You won!!!");
    }
    System.out.print("\nPlay again? (Enter Y|y to play or N|n to exit): ");
    String play = console.next();
    return play.charAt(0);
  }

  // Runnable Tester
  public static void main(String[] args) {
    Scanner console = new Scanner(System.in);
    new ConnectFour(false, console);
  }
}
