import java.util.Random;

/**
 * Created by davidkwon on 2017-07-25.
 */
public class Player {

    private String name;
    private String sign;
    private boolean isAI, firstMove;

    public Player (String name, String sign, boolean isAI) {
        this.name = name;
        this.sign = sign;
        this.isAI = isAI;
    }

    public Player (String name, String sign, boolean isAI, GameBoard board) {
        this.name = name;
        this.sign = sign;
        this.isAI = isAI;
        this.firstMove = true;
    }

    public int[] getMove(GameBoard board) {
        if (!this.isAI) {

            InputValidator input = new InputValidator();
            return input.getMoveInput(board);
        }else{

            if (firstMove) {
                firstMove  = false;
                int[] move = new int[2];
                if (board.isEmpty(1, 1)) {
                    move[0] = 1;
                    move[1] = 1;
                }else {
                    // the corners, for a 3x3 game
                    int[][] moves = {{0, 0},{0, 2},{2, 2},{2, 0}};
                    Random rand = new Random();
//                    move = moves[rand.nextInt(4)];
                    move = moves[1];
                }
                return move;
            }

            System.out.println("getting the move for player " + this.sign + "with this board:\n" + board.toString());
            long start = System.nanoTime();
            MinimaxTree minimax = new MinimaxTree(board, this.sign);
            minimax.generateTree(0, -2147483648, 2147483647);
            long elapsedTime = System.nanoTime() - start;
            System.out.println("Generating the tree took: "+elapsedTime/ 1000000+" milliseconds.");

            return minimax.getMove();
        }
    }

    public String getName() {
        return name;
    }

    public String getSign() { return sign; }

}
