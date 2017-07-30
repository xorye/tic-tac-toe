import java.util.Arrays;

/**
 * Created by davidkwon on 2017-07-25.
 */
public class GameBoard {

    private char[][] table;
    private boolean gameEnded = false;
    private int size, win, ignore, distance, numOfDiagArrays;


    public GameBoard(int size, int win) {

        this.size = size;
        this.win = win;
        this.numOfDiagArrays = (size * 4) - 2;

        this.table = new char[size][size];

        for (int i = 0; i < table.length; i++) {
            for (int j = 0; j < table[0].length; j++) {
                table[i][j] = '.';
            }
        }

    }

    public boolean addMove(int[] move, String sign) {

        this.table[move[1]][move[0]] = sign.charAt(0);

        return true;
    }

    public boolean isGameEnded() {
        return gameEnded;
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

    public void setGameEnded(boolean gameEnded) {
        this.gameEnded = gameEnded;
    }

    public boolean gameEnded() {
        return gameEnded;
    }

    public int getSize() {
        return size;
    }

    public int getWin() {
        return win;
    }


    public int getNumOfDiagArrays() {
        return numOfDiagArrays;
    }

    public char[][] getGameTable() {
        return table;
    }
}
