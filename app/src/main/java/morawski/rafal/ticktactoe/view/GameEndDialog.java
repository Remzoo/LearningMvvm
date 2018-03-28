package morawski.rafal.ticktactoe.view;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import morawski.rafal.ticktactoe.R;

/**
 * Created by r.morawski on 3/28/2018.
 */

public class GameEndDialog extends AppCompatDialogFragment {

    @BindView(R.id.winnerName)
    TextView winnerName;

    private GameActivity gameActivity;
    private String winner;

    public static GameEndDialog getInstance(GameActivity activity, String winner) {
        GameEndDialog gameEndDialog = new GameEndDialog();
        gameEndDialog.gameActivity = activity;
        gameEndDialog.winner = winner;
        return gameEndDialog;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View view = gameActivity.getLayoutInflater().inflate(R.layout.dialog_game_end, null);
        ButterKnife.bind(this, view);

        winnerName.setText(winner);

        AlertDialog alertDialog = new AlertDialog.Builder(getContext())
                .setView(view)
                .setCancelable(false)
                .setPositiveButton("Done", ((dialog, which) -> onNewGame()))
                .create();
        alertDialog.setCancelable(false);
        alertDialog.setCanceledOnTouchOutside(false);

        return alertDialog;
    }

    private void onNewGame() {
        dismiss();
        gameActivity.promptForPlayers();
    }
}
