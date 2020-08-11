package dam_45133.virusmaze.Activities;

import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.Query;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Objects;

import dam_45133.virusmaze.R;
import dam_45133.virusmaze.Utils.GameConstants;

public class ScoresActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private FirebaseUser currentUser;
    private Query myTopPostsQuery;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scores);
        Firebase.setAndroidContext(this);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        if (GameConstants.PLAY_MUSIC) {
            MenuActivity.getPlayer().start();
        }

        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();

        Spinner dropdown = findViewById(R.id.difficulty);
        //create a list of items for the spinner.
        String[] items = new String[]{getResources().getString(R.string.easyDifficulty), getResources().getString(R.string.mediumDifficulty),
                getResources().getString(R.string.hardDifficulty), getResources().getString(R.string.expertDifficulty)};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        dropdown.setAdapter(adapter);

        dropdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view,
                                       int position, long id) {
                switch (position) {
                    case 0:
                        clearScroll();
                        myTopPostsQuery = new Firebase("https://virusmaze-bb1cc.firebaseio.com/Users/Scores/" + currentUser.getUid() + "/" + getResources().getString(R.string.easyDifficulty)).
                                orderByValue().limitToLast(10);
                        myTopPostsQuery.addChildEventListener(new ChildEventListener() {
                            @Override
                            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                                String date_time = dataSnapshot.getKey();
                                String score = String.valueOf(dataSnapshot.getValue());
                                String date = date_time.split("at")[0];
                                addHighscore(date, score);
                            }

                            @Override
                            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

                            }

                            @Override
                            public void onChildRemoved(DataSnapshot dataSnapshot) {

                            }

                            @Override
                            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

                            }

                            @Override
                            public void onCancelled(FirebaseError firebaseError) {

                            }
                        });
                        break;
                    case 1:
                        clearScroll();
                        myTopPostsQuery = new Firebase("https://virusmaze-bb1cc.firebaseio.com/Users/Scores/" + currentUser.getUid() + "/" + getResources().getString(R.string.mediumDifficulty))
                                .orderByValue().limitToLast(10);
                        myTopPostsQuery.addChildEventListener(new ChildEventListener() {
                            @Override
                            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                                String date_time = dataSnapshot.getKey();
                                String score = String.valueOf(dataSnapshot.getValue());
                                String date = date_time.split("at")[0];
                                addHighscore(date, score);
                            }

                            @Override
                            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

                            }

                            @Override
                            public void onChildRemoved(DataSnapshot dataSnapshot) {

                            }

                            @Override
                            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

                            }

                            @Override
                            public void onCancelled(FirebaseError firebaseError) {

                            }
                        });
                        break;
                    case 2:
                        clearScroll();
                        myTopPostsQuery = new Firebase("https://virusmaze-bb1cc.firebaseio.com/Users/Scores/" + currentUser.getUid() + "/" + getResources().getString(R.string.hardDifficulty)).
                                orderByValue().limitToLast(10);
                        myTopPostsQuery.addChildEventListener(new ChildEventListener() {
                            @Override
                            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                                String date_time = dataSnapshot.getKey();
                                String score = String.valueOf(dataSnapshot.getValue());
                                String date = date_time.split("at")[0];
                                addHighscore(date, score);
                            }

                            @Override
                            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

                            }

                            @Override
                            public void onChildRemoved(DataSnapshot dataSnapshot) {

                            }

                            @Override
                            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

                            }

                            @Override
                            public void onCancelled(FirebaseError firebaseError) {

                            }
                        });
                        break;
                    case 3:
                        clearScroll();
                        myTopPostsQuery = new Firebase("https://virusmaze-bb1cc.firebaseio.com/Users/Scores/" + currentUser.getUid() + "/" + getResources().getString(R.string.expertDifficulty)).
                                orderByValue().limitToLast(10);
                        myTopPostsQuery.addChildEventListener(new ChildEventListener() {
                            @Override
                            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                                String date_time = dataSnapshot.getKey();
                                String score = String.valueOf(dataSnapshot.getValue());
                                String date = date_time.split("at")[0];
                                addHighscore(date, score);
                            }

                            @Override
                            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

                            }

                            @Override
                            public void onChildRemoved(DataSnapshot dataSnapshot) {

                            }

                            @Override
                            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

                            }

                            @Override
                            public void onCancelled(FirebaseError firebaseError) {

                            }
                        });
                        break;

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

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

    protected void addHighscore(String date, String highscore) {
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        params.setMargins(40, 5, 40, 0);

        TextView tv = new TextView(this);
        tv.setId(currentUser.getUid().hashCode());
        tv.setLayoutParams(params);
        SpannableString span1 = new SpannableString(date);
        span1.setSpan(new AbsoluteSizeSpan(80), 0, date.length(), Spanned.SPAN_INCLUSIVE_INCLUSIVE);

        SpannableString span2 = new SpannableString(highscore);
        span2.setSpan(new AbsoluteSizeSpan(100), 0, highscore.length(), Spanned.SPAN_INCLUSIVE_INCLUSIVE);
        span2.setSpan(new ForegroundColorSpan(Color.RED), 0, highscore.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        // let's put both spans together with a separator and all
        CharSequence finalText = TextUtils.concat(span1, ": ", span2);
        tv.setText(finalText);
        tv.setTextColor(Color.WHITE);

        LinearLayout scrollLayout = findViewById(R.id.scrollLayout);
        scrollLayout.addView(tv);

    }

    private void clearScroll() {
        LinearLayout scrollLayout = findViewById(R.id.scrollLayout);
        scrollLayout.removeAllViews();
    }
}
