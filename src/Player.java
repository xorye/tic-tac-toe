/**
 * Created by davidkwon on 2017-07-25.
 */
public class Player {

    private String name;
    private String sign;
    private int moves = 0;
    private int[] moveHistory;
    private int win;

    public Player (String name, String sign, GameBoard board) {
        this.name = name;
        this.sign = sign;
        // find out how many arrays we need
        moveHistory = new int[board.getNumOfDiagArrays() + (board.getSize() * 2)];
        this.win = board.getWin();


    }

    public boolean addMove(int[] move, int size) {

        // record the row
        this.moveHistory[move[0]] ++;

        // record the column
        this.moveHistory[size + move[1]] ++;

        // record diagonals
        int[][] diagPoints = new int[2][2];

        return true;
    }

    private int[][] getDiagPoints(int[] move, int size) {

        // find the left diagonal point





    }

    public String getName() {
        return name;
    }

    public String getSign() { return sign; }

}
