/**
 * Created by davidkwon on 2017-07-31.
 */
public class State {

    GameBoard board;
    Player currentPlayer;

    public State(GameBoard board, Player currentPlayer) {
        this.board = board;
        this.currentPlayer = currentPlayer;
    }

    public Player player() {
        return this.currentPlayer;
    }

    // return an array of all possible actions in index format
    public int[][] actions() {
        //count how many empty spaces there are
        int spaces = numOfEmptySpaces();
        int[][] moveset = new int[spaces][2];
        int index = 0;

        for (int i = 0; i < this.board.getGameTable()[0].length; i++) {
            for (int j = 0; j < this.board.getGameTable().length; j++) {
                if (board.getGameTable()[i][j] == '.'){
                    int[] move = new int[2];
                    move[0] = j;
                    move[1] = i;
                    moveset[index++] = move;
                }
            }
        }
        return moveset;
    }

    // return the state after action action is taken on this state
    public State result(int[] action) {

    }

    // return true if state is a terminal state
    public boolean terminal() {

    }

    // return the utility in this state, for player
    public int utility(Player player) {

    }

    private int numOfEmptySpaces() {
        int spaces = 0;

        for (int i = 0; i < this.board.getGameTable()[0].length; i++) {
            for (int j = 0; j < this.board.getGameTable().length; j++) {
                if (board.getGameTable()[i][j] == '.') spaces++;
            }
        }

        return spaces;
    }

    private State copy() {
        return new State(this.board, this.currentPlayer);
    }
}
