/**
 * Created by davidkwon on 2017-07-25.
 */
public class Player {

    private String name;
    private int moves = 0;
    private int[] moveHistory;

    public Player (String name, int size) {
        this.name = name;
        this.moveHistory = new int[size][size];

        for (int i = 0; i < size; i++) {
        }
    }

    public String getName() {
        return name;
    }

}
