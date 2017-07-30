/**
 * Created by davidkwon on 2017-07-29.
 */

import java.lang.Math;

public class MoveConverter {



    public static void convertToIndices(int[] move, int size) {

        move[0] = move[0] - 1;
        move[1] = move[1] - size;

        if (move[1] < 0) {
            move[1] = Math.abs(move[1]);
        }

    }

    public static void convertToCoordinates(int[] move, int size) {
        move[0]++;
        move[1] = (move[1] * -1) + size;

    }
}
