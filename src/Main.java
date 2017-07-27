/**
 * Created by davidkwon on 2017-07-25.
 */
import java.util.Scanner;
import java.util.LinkedList;
public class Main {

    public static void main(String args[]) {
        System.out.println("hello world");
        gameInit();
    }

    public static void gameInit() {
        InputValidator input = new InputValidator();
        int size = 3, win = 3;

        System.out.println("Welcome to tic tac toe!");

        // Get the desired size of the board
        size = input.getIntegerInput("Please indicate your board size (n x n): ");
        win = input.getIntegerInput("Please indicate how many in a row to win: ");

        // Inititialize the gameboard;
        GameBoard gameBoard = new GameBoard(size);

        beginGame(gameBoard, size, win);
    }

    public static void beginGame(GameBoard board, int size, int win) {
        InputValidator input = new InputValidator();
        LinkedList<Player> queue = new LinkedList<Player>();

        Player player1 = new Player("David", size, win);
        Player player2 = new Player("Divad", size, win);
        queue.add(player1);
        queue.add(player2);

        System.out.println("Who will go first?");
        System.out.println(player1.getName() + " will go first.\n");


        while (!board.checkIfGameEnded()) {
            Player currentPlayer = queue.removeFirst();
            System.out.println("Player 1: " + player1.getName() + " --- x");
            System.out.println("Player 2: " + player2.getName() + " --- o");
            System.out.println(board.toString());

            System.out.println("\n"+currentPlayer.getName()+", it is your turn.");
            int[] move = input.getMoveInput();



        }
    }
}
