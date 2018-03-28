package morawski.rafal.ticktactoe.model;

/**
 * Created by Rafal on 2018-03-28.
 */

public class Player {

    private String name;
    private String sign;

    public Player(String name, String sign) {
        this.name = name;
        this.sign = sign;
    }

    public String getName() {
        return name;
    }

    public String getSign() {
        return sign;
    }
}
