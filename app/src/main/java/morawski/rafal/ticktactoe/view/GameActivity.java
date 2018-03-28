package morawski.rafal.ticktactoe.view;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.util.Observable;
import java.util.Observer;

import morawski.rafal.ticktactoe.R;
import morawski.rafal.ticktactoe.databinding.ActivityGameBinding;
import morawski.rafal.ticktactoe.viewmodel.GameViewModel;

public class GameActivity extends AppCompatActivity implements Observer{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        promptForPlayers();
    }

    public void promptForPlayers() {
        GameBeginDialog dialog = GameBeginDialog.getInstance(this);
        dialog.show(getSupportFragmentManager(), "game_begin_tag");
    }

    public void onPlayersSet(String player1Name, String player2Name) {
        initDataBinding(player1Name, player2Name);
    }

    private void initDataBinding(String player1Name, String player2Name) {
        ActivityGameBinding activityGameBinding = DataBindingUtil.setContentView(this, R.layout.activity_game);
        GameViewModel gameViewModel = new GameViewModel(player1Name, player2Name);
        gameViewModel.addObserver(this);
        activityGameBinding.setGameViewModel(gameViewModel);
    }

    @Override
    public void update(Observable observable, Object o) {
        Log.i("TickTacToe", "Player " + o + " won!");
        GameEndDialog gameEndDialog = GameEndDialog.getInstance(this, (String) o);
        gameEndDialog.show(getSupportFragmentManager(), "game_end_tag");
    }
}
