/**
 * Created by davidkwon on 2017-07-31.
 */
public class State {

    GameBoard board;
    Player currentPlayer;
    int[] prevAction;

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
        State newState = this.copy();
        newState.performAction(action); // TODO after we perform the action, we should also change the current player to the next player
        newState.prevAction = action;
        return newState;
    }

    // return true if state is a terminal state
    public boolean terminal() {
        return this.board.isGameEnded() || this.board.isFull();
    }

    // return the utility in this state, for player
    public int utility(Player player) {
        String playerSign = player.getSign();

        // check if a player has won
        if (playerSign.charAt(0) == this.board.getWinningSign().charAt(0)) return this.board.getSize() * 2;
        else if (playerSign.charAt(0) != '.') return 0;

        return searchUtility(playerSign.charAt(0));
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

    private boolean performAction(int[] action) {
        this.board.addMove(action, this.currentPlayer.getSign());
        return true;
    }

    private int searchUtility(char sign) {
        int utility = 0;

        utility += searchHorizontalUtility(sign);

        return utility;
    }

    private int searchHorizontalUtility(char sign) {
        int[] position = this.prevAction;

        // search to the left
        int x = position[1] - 1, leftLength = 0, rightLength = 0, utility = 0, numOfSigns = 0, numOfSignsDelta = 0;
        char currentSpot = this.board.getGameTable()[position[0]][x];

        while (x > 0 && (currentSpot == sign || currentSpot == '.') && leftLength < this.board.getWin() - 1) {
            leftLength++;
            currentSpot = this.board.getGameTable()[position[0]][--x];
            if (currentSpot == sign) numOfSignsDelta++;
        }

        if (numOfSignsDelta == this.board.getWin() - 2) utility++;
        numOfSigns += numOfSignsDelta;

        // search to the right
        x = position[1] + 1;
        currentSpot = this.board.getGameTable()[position[0]][x];
        while (x < this.board.getSize() && (currentSpot == sign || currentSpot == '.') && rightLength < this.board.getWin() - 1) {
            rightLength++;
            currentSpot = this.board.getGameTable()[position[0]][++x];
            if (currentSpot == sign) numOfSignsDelta++;
        }

        if (numOfSignsDelta == this.board.getWin() - 2) utility++;
        numOfSigns += numOfSignsDelta;


        if (leftLength == this.board.getWin() - 1) {
            utility ++;
        }

        if (rightLength == this.board.getWin() - 1) {
            utility++;
        }

        return utility;

    }

    // first element in the list is utility, second is length TODO I left off here!!!!!!!!
    private int serachHorizontalLeftRight(int[] position, char sign, String direction) {
        int x = direction.equals("left") ? position[1] - 1 : position[1] + 1;
        int length = 0, utility = 0, numOfSigns = 0;
        char currentSpot = this.board.getGameTable()[position[0]][x];

        if (direction.equals("left")) {
            while (x > 0 && (currentSpot == sign || currentSpot == '.') && length < this.board.getWin() - 1) {
                length++;
                currentSpot = this.board.getGameTable()[position[0]][--x];
                if (currentSpot == sign) numOfSigns++;
            }
        }else {
            while (x < this.board.getSize() && (currentSpot == sign || currentSpot == '.') && length < this.board.getWin() - 1) {
                length++;
                currentSpot = this.board.getGameTable()[position[0]][++x];
                if (currentSpot == sign) numOfSigns++;
            }
        }


        if (length == this.board.getWin() - 1) {
            utility ++;
            utility += numOfSigns;

            // if you are one away from winning, get some more utility
            if (numOfSigns == this.board.getWin() - 2) utility++;
        }

        return utility;

    }

    private State copy() {
        GameBoard newBoard = this.board.copy();
        return new State(newBoard, this.currentPlayer);
    }
}
