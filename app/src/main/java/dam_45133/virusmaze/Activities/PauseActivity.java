package dam_45133.virusmaze.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import dam_45133.virusmaze.R;
import dam_45133.virusmaze.Utils.GameConstants;

public class PauseActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pause);
        if (GameConstants.PLAY_MUSIC) {
            MenuActivity.getPlayer().start();
        }
    }

    // Method to start activity for Help button
    public void showHelpScreen(View view) {
        Intent helpIntent = new Intent(this, HelpActivity.class);
        startActivity(helpIntent);
    }

    // Method to resume the game
    public void resumeGame(View view) {
        Intent resumeIntent = new Intent(this, PlayActivity.class);
        resumeIntent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivity(resumeIntent);
        this.finish();
    }


    @Override
    public void onResume() {
        super.onResume();
        if (GameConstants.PLAY_MUSIC) {
            MenuActivity.getPlayer().start();
        }
    }
}
