import java.util.Arrays;

/**
 * Created by davidkwon on 2017-07-25.
 */
public class GameBoard {

    private char[][] table;
    private int[][] moveHistory;
    private boolean gameEnded = false;
    private int size;
    private int winningLength;


    public GameBoard(int size) {
        this.table = new char[size][size];

        for (int i = 0; i < table.length; i++) {
            for (int j = 0; j < table[0].length; j++) {
                table[i][j] = '.';
            }
        }

    }

    public boolean checkIfGameEnded() {
        for (int i = 0; i < this.size; i++) {
            for (int j = 0; j < this.size; j++) {
                if (moveHistory[i][j] == winningLength) return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        // Using StringBuilder instead of concatening strings
        StringBuilder str = new StringBuilder((this.size + 1) * (this.size + 1));

        for (int i = 0; i < table.length; i++) {
            for (int j = 0; j < table[0].length; j++) {
                str.append(Character.toString(table[i][j]));
                str.append(" ");
            }

            // Append guide numbers at the right of the board
            str.append(Integer.toString(table.length - i));
            str.append("\n");
        }

        // Append guide numbers at the bottom of the board
        for (int i = 1; i <= table.length; i++) {
            str.append(Integer.toString(i));
            str.append(" ");
        }

        return str.toString();
    }

    public boolean gameEnded() {
        return gameEnded;
    }

    public int getSize() {
        return size;
    }



}
