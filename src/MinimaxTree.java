/**
 * Created by davidkwon on 2017-08-02.
 */
import java.util.ArrayList;

public class MinimaxTree {
    private ArrayList<MinimaxTree> children = new ArrayList<MinimaxTree>();
    private GameBoard board;
    private int[] move = {-1,-1};
    private int value;
    private String sign;


    public MinimaxTree(GameBoard board, String sign) {
        this.board = board;
        this.sign = sign;
        if (sign.equals("o")) this.value = -2147483648;
        else this.value = 2147483647;
    }

    public void generateTree(int depth, int alpha, int beta) {

        if (this.board.isGameEnded()) {
            this.value = this.getValue(depth);
            return;
        }

        for (int i = 0; i < this.board.getSize(); i++) {
            for (int j = 0; j < this.board.getSize(); j++) {

                if (this.board.isEmpty(i, j)) {
                    int[] move = new int[2];
                    move[0] = j;
                    move[1] = i;

                    // if this is a maximizing node and its value is less than beta, we need to look at child.
                    if (this.sign.equals("o") && this.value <= beta) {
                        alpha = createMaxChild(this.board, move, this.sign, depth, alpha, beta);
                    }

                    // if this is a minimizing node and its value is less than alpha, we need to look at child.
                    else if (this.sign.equals("x") && this.value >= alpha) {
                        beta = createMinChild(this.board, move, this.sign, depth, alpha, beta);
                    }
                }
            }
        }
    }

    public int createMinChild(GameBoard board, int[] move, String sign, int depth, int alpha, int beta) {
        board.addMove(move, sign);
        MinimaxTree child = new MinimaxTree(board, sign.equals("x") ? "o" : "x");
        child.move = move;
        child.generateTree(depth + 1, alpha, beta);
        board.removeMove(move);
        this.addChild(child);
        if (this.value > child.value) this.value = child.value;
        if (this.value < beta) beta = this.value;
        return beta;
    }

    public int createMaxChild(GameBoard board, int[] move, String sign, int depth, int alpha, int beta) {
        board.addMove(move, sign);
        MinimaxTree child = new MinimaxTree(board, sign.equals("x") ? "o" : "x");
        child.move = move;
        child.generateTree(depth + 1, alpha, beta);
        board.removeMove(move);
        this.addChild(child);
        if (this.value < child.value) this.value = child.value;
        if (this.value > alpha) alpha = this.value;
        return alpha;
    }

    public int[] getMove() {

        int[] move = new int[2];

        for (MinimaxTree tree: this.children) {

            if (tree.value == this.value) {
                move = tree.move;
            }
        }

        return move;
    }

    public int getValue(int depth) {
        char winningSign = this.board.getWinningSign();
        if (winningSign == 'x') {
            return depth - 10;
        } else if (winningSign == 'o') {
            return 10 - depth;
        } else {
            return 0;
        }
    }

    public void addChild(MinimaxTree child) {
        this.children.add(child);
    }

    public int getMaxValue() {
        int max = -2147483648;
        for (MinimaxTree tree : this.children) {
            if (tree.getValue() > max) max = tree.getValue();
        }
        return max;
    }

    public int getMinValue() {
        int min = 2147483647;
        for (MinimaxTree tree : this.children) {
            if (tree.getValue() < min) min = tree.getValue();
        }
        return min;
    }

    public int getValue() {
        return this.value;
    }
}