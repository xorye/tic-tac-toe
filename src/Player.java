/**
 * Created by davidkwon on 2017-07-25.
 */
public class Player {

    private String name;
    private int moves = 0;
    private int[][] moveHistory;
    private int win;

    public Player (String name, int size, int win) {
        this.name = name;

        // find out how many arrays we need
        int  ignore = win - 1;
        int distance = size - win;
        int numOfDiagArrays = (size - ignore + distance) * 2;
        moveHistory = new int[numOfDiagArrays][size];
        this.win = win;


    }

    public String getName() {
        return name;
    }

}
