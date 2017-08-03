/**
 * Created by davidkwon on 2017-08-02.
 */
import java.util.ArrayList;
import java.util.Random;

public class MinimaxTree {
    private ArrayList<MinimaxTree> children = new ArrayList<MinimaxTree>();
    private GameBoard board;
    private int[] move;
    private int value;

    public MinimaxTree(GameBoard board) {
        this.board = board;
    }

    public void generateTree(String sign, int depth) {

        if (this.board.isGameEnded()) {
            char winningSign = this.board.getWinningSign();
            if (winningSign == 'x') {
                this.value = depth - 10;
            } else if (winningSign == 'o') {
                this.value = 10 - depth;
            } else {
                this.value = 0;
            }
            return;
        }


        for (int i = 0; i < this.board.getSize(); i++) {
            for (int j = 0; j < this.board.getSize(); j++) {

                if (this.board.isEmpty(i, j)) {
                    int[] move = new int[2];
                    move[0] = j;
                    move[1] = i;
                    this.board.addMove(move, sign);
                    MinimaxTree child = new MinimaxTree(this.board);
                    child.move = move;
                    child.generateTree(sign.equals("x") ? "o" : "x", depth + 1);
                    this.board.removeMove(move);
                    this.addChild(child);
                    this.board.setGameEnded(false);
                }
            }
        }

        if (sign.equals("o")) this.value = this.getMaxValue();
        else this.value = this.getMinValue();

    }

    public int[] getMove() {

        int[] move = new int[2];


        for (MinimaxTree tree: this.children) {
            if (tree.value == this.value) {
                move = tree.move;
                System.out.println("Valid moves: ");
                System.out.println(Integer.toString(tree.move[0]) +","+ Integer.toString(tree.move[1]));
            }
        }

        return move;
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
