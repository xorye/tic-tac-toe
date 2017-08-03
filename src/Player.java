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
                    move = moves[rand.nextInt(4)];
                }
                return move;
            }

            MinimaxTree minimax = new MinimaxTree(board);
            minimax.generateTree(this.sign, 0);
            return minimax.getMove();
        }
    }

    public String getName() {
        return name;
    }

    public String getSign() { return sign; }

}
