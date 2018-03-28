package morawski.rafal.ticktactoe.view;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatDialogFragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import morawski.rafal.ticktactoe.R;

/**
 * Created by r.morawski on 3/28/2018.
 */

public class GameBeginDialog extends AppCompatDialogFragment {

    @BindView(R.id.player1Layout)
    TextInputLayout player1Layout;

    @BindView(R.id.player1Input)
    TextInputEditText player1Input;

    @BindView(R.id.player2Layout)
    TextInputLayout player2Layout;

    @BindView(R.id.player2Input)
    TextInputEditText player2Input;

    private GameActivity gameActivity;
    private String player1Name;
    private String player2Name;

    private static GameBeginDialog gameBeginDialog;

    public static GameBeginDialog getInstance(GameActivity activity) {
        if(gameBeginDialog == null) {
            gameBeginDialog = new GameBeginDialog();
            gameBeginDialog.gameActivity = activity;
        }
        return gameBeginDialog;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View view = gameActivity.getLayoutInflater().inflate(R.layout.dialog_game_begin, null);
        ButterKnife.bind(this, view);

        addTextWatchers();

        AlertDialog dialog = new AlertDialog.Builder(getContext())
                .setView(view)
                .setTitle("Enter names of players")
                .setCancelable(false)
                .setPositiveButton("Done", null)
                .create();
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setOnShowListener(alertDialog -> onShowDialog(dialog));

        return dialog;
    }

    private void onShowDialog(AlertDialog dialog) {
        Button positiveButton = dialog.getButton(AlertDialog.BUTTON_POSITIVE);
        positiveButton.setOnClickListener(v -> onDoneClicked());
    }

    private void onDoneClicked() {
        if(isValidName(player1Layout, player1Name) && isValidName(player2Layout, player2Name)) {
            gameActivity.onPlayersSet(player1Name, player2Name);
            dismiss();
        }
    }

    private boolean isValidName(TextInputLayout layout, String name) {
        if(name == null || name.isEmpty() || name.length() == 0) {
            layout.setErrorEnabled(true);
            layout.setError("Name is empty");
            return false;
        }

        if(player1Name != null && player2Name != null && player1Name.equals(player2Name)) {
            layout.setErrorEnabled(true);
            layout.setError("Same names");
            return false;
        }

        layout.setErrorEnabled(false);
        layout.setError("");
        return true;
    }

    private void addTextWatchers() {
        player1Input.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                player1Name = s.toString();
            }
        });

        player2Input.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                player2Name = s.toString();
            }
        });
    }
}
