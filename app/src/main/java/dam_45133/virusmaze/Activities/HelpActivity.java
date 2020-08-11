package dam_45133.virusmaze.Activities;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Objects;

import dam_45133.virusmaze.R;
import dam_45133.virusmaze.Utils.GameConstants;

public class HelpActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.help_layout);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        if (GameConstants.PLAY_MUSIC) {
            MenuActivity.getPlayer().start();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
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

}
