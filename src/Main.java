/**
 * Created by davidkwon on 2017-07-25.
 */
import java.util.Scanner;
import java.util.LinkedList;
public class Main {

    public static void main(String args[]) {
//        gameInit();

        GameBoard board = new GameBoard(4, 4);

        System.out.println("Board: \n" + board.toString());


        MinimaxTree tree = new MinimaxTree(board, "o");

        long start = System.nanoTime();
        tree.generateTree(0, -2147483648, 2147483647);
        long elapsedTime = System.nanoTime() - start;
        System.out.println("Generating the tree took: "+elapsedTime/ 1000000+" milliseconds.");

    }

    public static void gameInit() {
        InputValidator input = new InputValidator();
        int size = 3, win = 3;

        System.out.println("Welcome to tic tac toe!");

        // Get the desired size of the board
        size = input.getIntegerInput("Please indicate your board size (n x n): ");
        win = input.getIntegerInput("Please indicate how many in a row to win: ");

        // Inititialize the gameboard;
        GameBoard gameBoard = new GameBoard(size, win);

        beginGame(gameBoard, size, win);
    }

    public static void beginGame(GameBoard board, int size, int win) {
        LinkedList<Player> queue = new LinkedList<Player>();

        Player player1 = new Player("David", "x", false);
        Player player2 = new Player("Neil", "o", true, board);
        queue.add(player1);
        queue.add(player2);

        System.out.println("Who will go first?");
        System.out.println(player1.getName() + " will go first.\n");


        while (!board.isGameEnded()) {
            Player currentPlayer = queue.removeFirst();
            queue.add(currentPlayer);
            System.out.println("Player 1: " + player1.getName() + " --- x");
            System.out.println("Player 2: " + player2.getName() + " --- o");
            System.out.println(board.toString());

            System.out.println("\n"+currentPlayer.getName()+", it is your turn.");

            // gets the move in [x, y] index format. This method also checks if the move is valid.
            int[] move = currentPlayer.getMove(board);

            //if valid, add it to the Player and GameBoard object
            board.addMove(move, currentPlayer.getSign());
        }
        System.out.println("Player 1: " + player1.getName() + " --- x");
        System.out.println("Player 2: " + player2.getName() + " --- o");
        System.out.println(board.toString());

    }



}
