/**
 * Created by davidkwon on 2017-07-25.
 */
public class Player {

    private String name;
    private String sign;

    public Player (String name, String sign, GameBoard board) {
        this.name = name;
        this.sign = sign;
    }

    public String getName() {
        return name;
    }

    public String getSign() { return sign; }

}
