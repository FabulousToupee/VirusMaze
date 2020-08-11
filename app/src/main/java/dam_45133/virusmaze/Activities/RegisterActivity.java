package dam_45133.virusmaze.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;

import dam_45133.virusmaze.R;

public class RegisterActivity extends AppCompatActivity {
    private EditText emailField;
    private EditText passwordField;
    private EditText confirmPasswordField;
    private Button registerButton;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        mAuth = FirebaseAuth.getInstance();

        emailField = findViewById(R.id.emailField);
        passwordField = findViewById(R.id.passwordField);
        confirmPasswordField = findViewById(R.id.passwordConfirmField);

        registerButton = findViewById(R.id.registerButton);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               createAccount();
            }
        });
    }

    private void createAccount(){
        String email = emailField.getText().toString();
        String password = passwordField.getText().toString();
        String confirmPassword = confirmPasswordField.getText().toString();
        if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password) || TextUtils.isEmpty(confirmPassword)){
            Toast.makeText(RegisterActivity.this,"Fields are empty", Toast.LENGTH_LONG).show();
        }
        else{
            if(password.equals(confirmPassword)){
                mAuth.createUserWithEmailAndPassword(email,password)
                        .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful()){
                                    Toast.makeText(RegisterActivity.this,"Account created successfully", Toast.LENGTH_LONG).show();
                                    startActivity(new Intent(RegisterActivity.this, MainActivity.class));
                                    mAuth.updateCurrentUser(null);
                                }
                                else{
                                    String error = task.getException().toString();
                                    String[] splitMessage = error.split(":");
                                    Toast.makeText(RegisterActivity.this,splitMessage[splitMessage.length - 1], Toast.LENGTH_LONG).show();
                                }
                            }
                        });
            }
            else{
                Toast.makeText(RegisterActivity.this,"Passwords don't match", Toast.LENGTH_LONG).show();
            }
        }

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
