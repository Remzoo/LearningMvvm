package morawski.rafal.ticktactoe.model;

/**
 * Created by Rafal on 2018-03-28.
 */

public class Cell {

    private Player player;

    public Cell(Player player) {
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }
}
