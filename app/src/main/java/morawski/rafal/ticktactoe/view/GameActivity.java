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
        setContentView(R.layout.activity_game);

        initDataBinding();
    }

    private void initDataBinding() {
        ActivityGameBinding activityGameBinding = DataBindingUtil.setContentView(this, R.layout.activity_game);
        GameViewModel gameViewModel = new GameViewModel("Zenek", "Karol");
        gameViewModel.addObserver(this);
        activityGameBinding.setGameViewModel(gameViewModel);
    }

    @Override
    public void update(Observable observable, Object o) {
        Log.i("TickTacToe", "Player " + o + " won!");
    }
}
