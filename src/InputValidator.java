/**
 * Created by davidkwon on 2017-07-27.
 */

import java.util.Scanner;
import java.lang.Math;

public class InputValidator {

    Scanner sc = new Scanner(System.in);

    public int getIntegerInput(String message) {
        boolean initialized = false;
        int result = 0;

        // Get the desired size of the board
        do {
            try {
                System.out.print(message);
                result = Integer.parseInt(sc.nextLine());
                initialized = true;
            } catch (NumberFormatException e) {
                System.out.println("Invalid! Try again.");
            }
        }while (!initialized);

        return result;
    }

    public int[] getMoveInput(GameBoard board) {
        boolean initialized = false;
        boolean validMove = false;
        int[] move = new int[2];

        do {

            if (validMove) validMove = false;

            try {
                System.out.println("Enter your move in this format (x,y)");
                move = getXYValues(sc.nextLine());
                initialized = true;
                if (move[0] > board.getSize() || move[1] > board.getSize() || move[0] < 1 || move[1] < 1)
                    throw new InvalidMoveException("You have entered an invalid move!");

                // convert the move into indices ie. (2,4) becomes (1, 0)
                move = convertToIndices(move, board.getSize());

                if (board.getGameTable()[move[1]][move[0]] != '.')
                    throw new InvalidMoveException("A player has already played there!");

                validMove = true;

            } catch (NumberFormatException|StringIndexOutOfBoundsException e){
                System.out.println(e);
                System.out.println("Invalid!");
            } catch (InvalidMoveException e) {
                System.out.println(e);
            }

        } while (!initialized || !validMove);

        return move;
    }

    private int[] getXYValues(String input) {

        int[] move = new int[2];
        int currentIndex = 1;
        char currentChar = input.charAt(currentIndex);

        boolean commaFound = false;
        String value = "";
        int x = 0, y;

        // while currentChar is a character representing a number between 0 and 9 inclusive
        while ((currentChar >= 48 && currentChar <= 57) || !commaFound) {

            if (currentChar < 48 || currentChar > 57) {
                commaFound = true;
                x = Integer.parseInt(value);
                value = "";

            }else {
                value += Character.toString(currentChar);
            }
            currentIndex++;
            currentChar = input.charAt(currentIndex);
        }

        y = Integer.parseInt(value);
        move[0] = x;
        move[1] = y;

        return move;
    }

    private int[] convertToIndices(int[] move, int size) {
        int[] convertedMove = new int[2];
        convertedMove[0] = move[0] - 1;
        convertedMove[1] = move[1] - size;

        if (convertedMove[1] < 0) {
            convertedMove[1] = Math.abs(convertedMove[1]);
        }

        return convertedMove;
    }
}
