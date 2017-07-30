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

    public boolean addMove(int[] move, GameBoard board) {
        board.addMove(move, this.getSign());

        // record the row
        this.moveHistory[move[0]]++;

        // record the column
        this.moveHistory[board.getSize() + move[1]]++;

        // get diagonal points
        int[][] diagPoints = getDiagPoints(move, board.getSize());
        // record diagonal points
        recordDiagPoints(diagPoints, board.getSize());

        //check if player won
        checkWin(board);

        return true;
    }

    private boolean recordDiagPoints(int[][] diagPoints, int size) {

        int[] left = diagPoints[0];
        int[] right = diagPoints[1];

        // record left diagonal point
        if (left[0] == 1) {
            this.moveHistory[(size * 3) - left[1]]++;
        }else {
            this.moveHistory[(size * 2) + 1 + left[0]]++;
        }

        // record right diagonal point
        if (right[0] == size) {
            this.moveHistory[(size * 5) - 1 - right[1]]++;
        }else {
            this.moveHistory[size + 1 - right[1]]++;
        }


        return true;
    }

    private int[][] getDiagPoints(int[] move, int size) {
        System.out.println("The size is: " + Integer.toString(size));

        int[] moveCopy = new int[2];
        System.arraycopy(move, 0, moveCopy, 0, 2);
        int[][] diagPoints = new int[2][2];
        int[] leftPoint = new int[2];
        int[] rightPoint = new int[2];
        MoveConverter converter = new MoveConverter();
        // convert move so that it is not in index format
        converter.convertToCoordinates(moveCopy, size);


        // move is on the main diagonal from top left to bottom right
        if (size - moveCopy[0] + 1 == moveCopy[1]) {
            diagPoints[1][0] = size - 1;
            diagPoints[1][1] = size - 1;
        }

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


        diagPoints[0] = leftPoint;
        diagPoints[1] = rightPoint;

        return diagPoints;

    }

    private boolean checkWin(GameBoard board) {
        for (int i : this.moveHistory) {
            if (i == this.win) {
                board.setGameEnded(true);
            }
        }
        System.out.println(this.name + " has won the game!");
        return true;
    }

    public String getName() {
        return name;
    }

    public String getSign() { return sign; }

}
