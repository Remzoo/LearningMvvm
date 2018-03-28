package morawski.rafal.ticktactoe.viewmodel;

import android.databinding.ObservableArrayMap;

import java.util.Observable;

import morawski.rafal.ticktactoe.model.Game;
import morawski.rafal.ticktactoe.model.Player;

/**
 * Created by Rafal on 2018-03-28.
 */

public class GameViewModel extends Observable {

    private static final String TAG = GameViewModel.class.getSimpleName();

    private Game game;
    public ObservableArrayMap<String, String> cells = new ObservableArrayMap<>();

    public GameViewModel(String player1, String player2) {
        game = new Game(new Player(player1, "X"), new Player(player2, "O"));
    }

    public void onClickedCellAt(int row, int column) {
        if(game.isEmptyCell(row, column) && !game.hasGameEnded()) {
            game.setPlayerAt(row, column);

            String key = new StringBuffer("").append(row).append(column).toString();
            cells.put(key, game.getCurrentPlayer().getSign());

            if(game.hasGameEnded()) {
                onGameEnded();
            }
            else {
                game.switchPlayer();
            }
        }
    }

    private void onGameEnded() {
        setChanged();
        notifyObservers(game.getWinner() == null ? "TIE" : game.getWinner().getName());
    }
}
