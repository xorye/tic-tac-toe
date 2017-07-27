/**
 * Created by davidkwon on 2017-07-27.
 */

import java.util.Scanner;

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

    public int[] getMoveInput() {
        boolean initialized = false;
        int[] move = new int[2];

        do {
            try {
                System.out.println("Enter your move in this format (x,y)");
                move = getXYValues(sc.nextLine());
                initialized = true;

            } catch (NumberFormatException e){
                System.out.println(e);
                System.out.println("Invalid!");
            }

        } while (!initialized);

        return move;
    }

    private int[] getXYValues(String input) {

        int[] move = new int[2];
        int currentIndex = 1;
        char currentChar = input.charAt(currentIndex);


        boolean commaFound = false;
        String value = "";
        int x = 0, y;

        while (currentChar <= 9 || !commaFound) {
            System.out.println("the value of x is: " + value);Ω¸Ω
            if (currentChar > 9) {
                commaFound = true;
                x = Integer.parseInt(value);

                value = "";

            }else {
                value += Character.toString(currentChar);
            }

            currentIndex ++;
            System.out.println("nyes");
            currentChar = input.charAt(currentIndex);
            System.out.println("nyes");
            System.out.println("The current character is: " + currentChar);
        }

        y = Integer.parseInt(value);
        move[0] = x;
        move[1] = y;

        System.out.println("The x value is: " + Integer.toString(x));
        System.out.println("The y value is: " + Integer.toString(y));
        return move;
    }
}
