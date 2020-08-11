package dam_45133.virusmaze.Activities;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Objects;

import dam_45133.virusmaze.R;
import dam_45133.virusmaze.Utils.GameConstants;

public class SettingsActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_layout);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        if (GameConstants.PLAY_MUSIC) {
            MenuActivity.getPlayer().start();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        MenuActivity.getPlayer().pause();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    public void playMusic(View view) {
        if (GameConstants.PLAY_MUSIC) {
            MenuActivity.getPlayer().pause();
            MenuActivity.getPlayer().seekTo(0);
            GameConstants.PLAY_MUSIC = false;
        } else {
            MenuActivity.getPlayer().start();
            GameConstants.PLAY_MUSIC = true;

        }
    }



    public void changeToEasyDifficulty(View view) {
        GameConstants.randomizeSpawn();
        GameConstants.resetGame("Easy");
        Toast.makeText(SettingsActivity.this, getResources().getString(R.string.levelDifficulty) + ": " + getResources().getString(R.string.easyDifficulty), Toast.LENGTH_SHORT).show();
    }

    public void changeToMediumDifficulty(View view) {
        GameConstants.randomizeSpawn();
        GameConstants.resetGame("Medium");
        Toast.makeText(SettingsActivity.this, getResources().getString(R.string.levelDifficulty) + ": " + getResources().getString(R.string.mediumDifficulty), Toast.LENGTH_SHORT).show();
    }

    public void changeToHardDifficulty(View view) {
        GameConstants.randomizeSpawn();
        GameConstants.resetGame("Hard");
        Toast.makeText(SettingsActivity.this, getResources().getString(R.string.levelDifficulty) + ": " + getResources().getString(R.string.hardDifficulty), Toast.LENGTH_SHORT).show();
    }

    public void changeToExpertDifficulty(View view) {
        GameConstants.randomizeSpawn();
        GameConstants.resetGame("Expert");
        Toast.makeText(SettingsActivity.this, getResources().getString(R.string.levelDifficulty) + ": " + getResources().getString(R.string.expertDifficulty), Toast.LENGTH_SHORT).show();
    }

}

