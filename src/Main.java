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

        int size = 3;

        Scanner sc = new Scanner(System.in);

        System.out.println("Welcome to tic tac toe!");


        boolean sizeInitialized = false;

        // Get the desired size of the board
        do {
            try {
                System.out.println("Please indicate your board size (n x n): ");
                size = Integer.parseInt(sc.nextLine());
                sizeInitialized = true;
            } catch (NumberFormatException e){
                System.out.println("Invalid! Try again.");
            }
        }while (!sizeInitialized);

        // Inititialize the gameboard;
        GameBoard gameBoard = new GameBoard(size);

        beginGame(gameBoard, size, sc);
    }

    public static void beginGame(GameBoard board, int size, Scanner sc) {

        LinkedList<Player> queue = new LinkedList<Player>();

        Player player1 = new Player("David", size);
        Player player2 = new Player("Divad", size);
        queue.add(player1);
        queue.add(player2);

        System.out.println("Who will go first?");
        System.out.println(player1.getName() + " will go first.\n");


        while (!board.checkIfGameEnded()) {
            Player currentPlayer = queue.removeFirst();
            System.out.println("Player 1: " + player1.getName() + " --- x");
            System.out.println("Player 2: " + player2.getName() + " --- o");
            System.out.println(board.toString());

            System.out.println("\n"+currentPlayer.getName()+", enter your move in this format (x,y) :");



        }
    }
}
