package dam_45133.virusmaze.Activities;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.firebase.client.Firebase;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import dam_45133.virusmaze.Model.CountDownTimerPausable;
import dam_45133.virusmaze.R;
import dam_45133.virusmaze.Utils.GameConstants;

public class GameOverActivity extends AppCompatActivity {
    private MediaPlayer player;
    private FirebaseAuth mAuth;
    private FirebaseUser currentUser;
    private Firebase mDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Firebase.setAndroidContext(this);
        setContentView(R.layout.activity_game_over);

        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();
        mDatabase = new Firebase("https://virusmaze-bb1cc.firebaseio.com");

        player = MediaPlayer.create(this, R.raw.gameover);
        player.setVolume(50, 50);
        player.start();

        new CountDownTimerPausable(player.getDuration(), 1000) {
            public void onTick(long millisUntilFinished) {
            }
            public void onFinish() {
                if(GameConstants.SCORE > 0)
                    logScore();
                GameConstants.resetGame(GameConstants.DIFFICULTY);
                Intent menuIntent = new Intent(GameOverActivity.this, MenuActivity.class);
                startActivity(menuIntent);
            }
        }.start();
    }

    private void logScore(){
        DateFormat df = new SimpleDateFormat("dd-MM-yyyy 'at' HH:mm:ss");
        String date = df.format(Calendar.getInstance().getTime());
        mDatabase.child("Users").child("Scores").child(currentUser.getUid()).child(GameConstants.DIFFICULTY).child(date).setValue(GameConstants.SCORE);
    }
}
