package dam_45133.virusmaze.Activities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;


import dam_45133.virusmaze.GameConstants;
import dam_45133.virusmaze.R;
import dam_45133.virusmaze.View.DrawingView;
import dam_45133.virusmaze.GameLoopLogic;
import yuku.ambilwarna.AmbilWarnaDialog;

public class PlayActivity extends AppCompatActivity{
    private DrawingView drawingView;
    private static GameLoopLogic gameLoopLogic;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (GameConstants.PLAY_MUSIC) {
            MenuActivity.getPlayer().start();
        }

        drawingView = new DrawingView(this);
        gameLoopLogic = new GameLoopLogic(drawingView);

        LinearLayout.LayoutParams params =
                new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT);

        FrameLayout game = new FrameLayout(this);
        LinearLayout gameWidgets = new LinearLayout (this);
        gameWidgets.setGravity(Gravity.CENTER | Gravity.BOTTOM);
        gameWidgets.setOrientation(LinearLayout.HORIZONTAL);

        Button pauseButton = new Button(this);
        pauseButton.setText(R.string.pause);
        pauseButton.setLayoutParams(params);
        gameWidgets.addView(pauseButton);
        pauseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pauseIntent = new Intent(PlayActivity.this, PauseActivity.class);
                startActivity(pauseIntent);
            }
        });

        Button settingsButton = new Button(this);
        settingsButton.setText(R.string.help);
        settingsButton.setLayoutParams(params);
        gameWidgets.addView(settingsButton);
        settingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent helpIntent = new Intent(PlayActivity.this, HelpActivity.class);
                startActivity(helpIntent);
            }
        });

        Button exitButton = new Button(this);
        exitButton.setText(R.string.exit);
        exitButton.setLayoutParams(params);
        exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which){
                            case DialogInterface.BUTTON_POSITIVE:
                                GameConstants.resetGame(GameConstants.DIFFICULTY);
                                Intent menuIntent = new Intent(PlayActivity.this, MenuActivity.class);
                                startActivity(menuIntent);
                                break;

                            case DialogInterface.BUTTON_NEGATIVE:
                                dialog.dismiss();
                                break;
                        }
                    }
                };

                AlertDialog.Builder builder = new AlertDialog.Builder(PlayActivity.this);
                builder.setMessage(R.string.areYouSure).setPositiveButton(R.string.yes, dialogClickListener)
                        .setNegativeButton(R.string.no, dialogClickListener).show();
            }
        });

        Button cosmeticsButton = new Button(this);
        cosmeticsButton.setText(R.string.colors);
        cosmeticsButton.setLayoutParams(params);
        gameWidgets.addView(cosmeticsButton);
        cosmeticsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openColorPicker();
            }
        });

        gameWidgets.addView(exitButton);

        if(drawingView.getParent() != null) {
            ((ViewGroup)drawingView.getParent()).removeView(drawingView);
        }
        game.addView(drawingView);
        game.addView(gameWidgets);

        setContentView(game);

    }

    @Override
    protected void onPause() {
        super.onPause();
        drawingView.timer.pause();
        gameLoopLogic.pause();

    }

    @Override
    protected void onResume() {
        super.onResume();
        drawingView.timer.start();
        gameLoopLogic.resume();
    }

    @Override
    public void onBackPressed() {
        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which){
                    case DialogInterface.BUTTON_POSITIVE:
                        GameConstants.resetGame(GameConstants.DIFFICULTY);
                        Intent menuIntent = new Intent(PlayActivity.this, MenuActivity.class);
                        startActivity(menuIntent);
                        break;

                    case DialogInterface.BUTTON_NEGATIVE:
                        dialog.dismiss();
                        break;
                }
            }
        };

        AlertDialog.Builder builder = new AlertDialog.Builder(PlayActivity.this);
            builder.setMessage(R.string.areYouSure).setPositiveButton(R.string.yes, dialogClickListener)
                .setNegativeButton(R.string.no, dialogClickListener).show();
    }

    public void openColorPicker(){
        AmbilWarnaDialog colorPicker = new AmbilWarnaDialog(this, GameConstants.BACKGROUND_COLOR, new AmbilWarnaDialog.OnAmbilWarnaListener() {
            @Override
            public void onCancel(AmbilWarnaDialog dialog) {

            }

            @Override
            public void onOk(AmbilWarnaDialog dialog, int color) {
                GameConstants.changeColor(color);
            }
        });
        colorPicker.show();
    }

    public static void gameOver(final Context mContext){
        Intent gameOverIntent = new Intent(mContext, GameOverActivity.class);
        mContext.startActivity(gameOverIntent);
    }

    public static void nextLevel(final Context mContext){
        Intent nextLevelIntent = new Intent(mContext, PlayActivity.class);
        mContext.startActivity(nextLevelIntent);
    }




}
