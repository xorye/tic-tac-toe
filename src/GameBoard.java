import java.util.Arrays;

/**
 * Created by davidkwon on 2017-07-25.
 */
public class GameBoard {

    private char[][] table;
    private boolean gameEnded = false;
    private int size, win;
    private char winningSign = '.';


    public GameBoard(int size, int win) {

        this.size = size;
        this.win = win;

        this.table = new char[size][size];

        for (int i = 0; i < table.length; i++) {
            for (int j = 0; j < table[0].length; j++) {
                table[i][j] = '.';
            }
        }

    }

    public boolean addMove(int[] move, String sign) {
        // add the move to the table
        this.table[move[1]][move[0]] = sign.charAt(0);

        // check if there is a winner
        checkIfWinner(move, sign);

        return true;
    }

    public boolean checkIfWinner(int[] move, String sign) {

        // check row, column and diagonals to see if there is a winnder
        if (checkRow(move, sign) || checkColumn(move, sign) || checkDiagonals(move, sign)) {
            this.gameEnded = true;
            this.winningSign = sign.charAt(0);
            System.out.println("There is a winner!! Congratulations!");
            return true;
        }

        return false;
    }

    private boolean checkRow(int[] move, String sign) {
        int consecutive = 0;
        for (int i = 0; i < table[0].length; i++) {
            if (table[move[0]][i] == sign.charAt(0)) {
                consecutive++;
            }else {
                consecutive = 0;
            }

            if (consecutive == this.win) {
                // we have a winnder
                return true;
            }
        }
        return false;
    }

    private boolean checkColumn(int[] move, String sign) {
        int consecutive = 0;
        for (int i = 0; i < table.length; i++) {
            if (table[i][move[1]] == sign.charAt(0)) {
                consecutive++;
            } else {
                consecutive = 0;
            }

            if (consecutive == this.win) {
                return true;
            }
        }
        return false;
    }

    private boolean checkDiagonals(int[] move, String sign) {
        int[][] diagPoints = getDiagPoints(move, this.size);
        int[] leftPoint = diagPoints[0];
        int[] rightPoint = diagPoints[1];
        int consecutive = 0;

        int x = leftPoint[0];
        int y = leftPoint[1];
        while (x < this.size && y >= 0) {
            if (this.table[y][x] == sign.charAt(0)) {
                consecutive++;
            } else {
                consecutive = 0;
            }
            if (consecutive == this.win) {
                return true;
            }
            x++;
            y--;
        }

        // check right column
        x = rightPoint[0];
        y = rightPoint[1];
        consecutive = 0;
        while (x >= 0 && y >= 0) {
            if (this.table[y][x] == sign.charAt(0)) {
                consecutive++;
            }else {
                consecutive = 0;
            }
            if (consecutive == this.win) {
                return true;
            }
            x--;
            y--;
        }

        return false;
    }

    // This method returns diagpoints in indices
    private int[][] getDiagPoints(int[] move, int size) {

        int[] moveCopy = new int[2];
        System.arraycopy(move, 0, moveCopy, 0, 2);
        int[][] diagPoints = new int[2][2];
        int[] leftPoint = new int[2];
        int[] rightPoint = new int[2];
        MoveConverter converter = new MoveConverter();
        // convert move so that it is not in index format
        converter.convertToCoordinates(moveCopy, size);


        int x = moveCopy[0];
        int y = moveCopy[1];

        // find left diagonal point
        while (x > 1 && y > 1) {
            x--;
            y--;
        }
        leftPoint[0] = x;
        leftPoint[1] = y;

        // find right diagonal point
        x = moveCopy[0];
        y = moveCopy[1];
        while (x < size  && y > 1) {
            x++;
            y--;
        }
        rightPoint[0] = x;
        rightPoint[1] = y;

        converter.convertToIndices(leftPoint, this.size);
        converter.convertToIndices(rightPoint, this.size);
        diagPoints[0] = leftPoint;
        diagPoints[1] = rightPoint;

        return diagPoints;
    }

    public boolean isFull() {
        for (char[] arr: this.table) {
            for (char ch: arr) {
                if (ch == '.') return false;
            }
        }
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

    public GameBoard copy() {
        char[][] copyTable = new char[this.size][this.size];

        for (int i = 0; i < copyTable[0].length; i++) {
            copyTable[i] = Arrays.copyOf(this.table[i], this.size);
        }

        GameBoard newBoard = new GameBoard(this.size, this.win);
        newBoard.setTable(copyTable);

        return newBoard;
    }

    private void setTable(char[][] newTable) {
        this.table = newTable;
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

    public String getWinningSign() { return this.getWinningSign(); }

    public char[][] getGameTable() {
        return table;
    }
}
