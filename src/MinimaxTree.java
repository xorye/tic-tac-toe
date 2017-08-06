/**
 * Created by davidkwon on 2017-08-02.
 */
import java.util.ArrayList;
import java.util.Random;

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
                        System.out.println("Maximizing node.");
                        this.board.addMove(move, sign);
                        System.out.println("New child. This is the board: \n" + this.board.toString());
                        MinimaxTree child = new MinimaxTree(this.board, this.sign.equals("x") ? "o" : "x");
                        child.move = move;
                        System.out.println("Passed down alpha: " + alpha + " Passed down beta: " + beta + ".");
                        child.generateTree(depth + 1, alpha, beta);
                        this.board.removeMove(move);
                        this.addChild(child);
                        this.board.setGameEnded(false);
                        System.out.println("The value for this maximizing node ("+this.move[0]+ "," +this.move[1]+") is: " + this.value);
                        if (this.value < child.value) {
                            System.out.println("The child value:" + child.value);
                            this.value = child.value;
                            System.out.println("The new value is: " + this.value);
                        }
                        if (this.value > alpha) {
                            alpha = this.value;
                            System.out.println("Thew new alpha to pass down is: " + alpha);
                        }
                    }

                    // if this is a minimizing node and its value is less than alpha, we need to look at child.
                    else if (this.sign.equals("x") && this.value >= alpha) {
                        System.out.println("Minimizing node.");
                        this.board.addMove(move, sign);
                        System.out.println("New child. This is the board: \n" + this.board.toString());
                        MinimaxTree child = new MinimaxTree(this.board, this.sign.equals("x") ? "o" : "x");
                        child.move = move;
                        System.out.println("Passed down alpha: " + alpha + " Passed down beta: " + beta + ".");
                        child.generateTree(depth + 1, alpha, beta);
                        this.board.removeMove(move);
                        this.addChild(child);
                        this.board.setGameEnded(false);
                        System.out.println("This value for this minimizing node ("+this.move[0]+ "," +this.move[1]+") is: " + this.value);
                        if (this.value > child.value) {
                            this.value = child.value;
                            System.out.println("The new value is: " + this.value);
                        }
                        if (this.value < beta) {
                            beta = this.value;
                            System.out.println("Thew new beta to pass down is: " + beta);
                        }
                    }
                }
            }
        }
//
//        if (sign.equals("o")) this.value = this.getMaxValue();
//        else this.value = this.getMinValue();

    }

    public int[] getMove() {

        int[] move = new int[2];

        for (MinimaxTree tree: this.children) {
            System.out.println("The value of child with move : " + tree.move[0] + ", " + tree.move[1] + " is: " + tree.value);
        }

        for (MinimaxTree tree: this.children) {

            if (tree.value == this.value) {
                move = tree.move;
                System.out.println("value is: " + this.value);
                System.out.println("Valid moves: ");
                System.out.println(Integer.toString(tree.move[0]) +","+ Integer.toString(tree.move[1]));
            }
        }

        return move;
    }

    public int getValue(int depth) {
        System.out.println("get value was called!!");
        char winningSign = this.board.getWinningSign();
        if (winningSign == 'x') {
            System.out.println("lose");
            return depth - 10;
        } else if (winningSign == 'o') {
            System.out.println("win");
            return 10 - depth;
        } else {
            System.out.println("draw");
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
