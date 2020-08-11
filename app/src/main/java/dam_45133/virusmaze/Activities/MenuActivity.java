package dam_45133.virusmaze.Activities;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

import dam_45133.virusmaze.R;
import dam_45133.virusmaze.Utils.GameConstants;

public class MenuActivity  extends AppCompatActivity {
    private static MediaPlayer player;
    private FirebaseAuth firebaseAuth;



    public void showHelpScreen(View view){
        Intent helpIntent = new Intent(this, HelpActivity.class);
        startActivity(helpIntent);
    }

    public void showSettingsScreen(View view){
        Intent settingIntent = new Intent(this, SettingsActivity.class);
        startActivity(settingIntent);
    }

    public void showPlayScreen(View view) {
        Intent playIntent = new Intent(this, PlayActivity.class);
        startActivity(playIntent);
    }
    public void showScoreScreen(View view){
        Intent scoreIntent = new Intent(this, ScoresActivity.class);
        startActivity(scoreIntent);
    }

    public void signOut(View view){
        if(firebaseAuth.getCurrentUser() != null) {
            firebaseAuth.signOut();
            Toast.makeText(MenuActivity.this, "Bye! See you next time.", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(MenuActivity.this, MainActivity.class));
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        if(GameConstants.PLAY_MUSIC && player == null) {
            player = MediaPlayer.create(this, R.raw.pacman_song);
            player.setVolume(50, 50);
            player.setLooping(true);
            player.start();
        }
        firebaseAuth = FirebaseAuth.getInstance();

    }

    @Override
    public void onPause() {
        super.onPause();
        player.pause();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (GameConstants.PLAY_MUSIC) {
            MenuActivity.getPlayer().start();
        }
    }

    @Override
    public void onBackPressed() {
        if (firebaseAuth.getCurrentUser() != null)
            Toast.makeText(MenuActivity.this, "Please logout to do that", Toast.LENGTH_SHORT).show();
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

    public static MediaPlayer getPlayer() {
        return player;
    }

}
