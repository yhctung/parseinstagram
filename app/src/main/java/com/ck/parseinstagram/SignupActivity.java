package com.ck.parseinstagram;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class SignupActivity extends AppCompatActivity {

    private static final String TAG = "LoginActivity";

    private EditText etNewUsername;
    private EditText etNewPassword;
    private EditText etNewEmail;
    private EditText etNewHandle;
    private Button btnDone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        btnDone = findViewById(R.id.btnDone);
        etNewUsername = findViewById(R.id.etNewUsername);
        etNewPassword = findViewById(R.id.etNewPassword);
        etNewEmail = findViewById(R.id.etNewEmail);
        etNewHandle = findViewById(R.id.etNewHandle);

        btnDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = etNewUsername.getText().toString();
                String password = etNewPassword.getText().toString();
                String email = etNewEmail.getText().toString();
                String handle = etNewHandle.getText().toString();
                signup(username, password, email, handle);
            }
        });
    }

    private void signup(String username, String password, String email, String handle) {
        // create ParseUser
        ParseUser user = new ParseUser();

        // Set information
        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(email);
        user.put("handle", handle);

        // Invoke signUpInBackground
        user.signUpInBackground(new SignUpCallback() {
            public void done(ParseException e) {
                if (e != null) {
                    // TODO: better error handling
                    Log.e(TAG, "Issue with signup");
                    e.printStackTrace();
                    return;
                }
            }
        });

        Toast.makeText(SignupActivity.this, "Account created!", Toast.LENGTH_SHORT).show();
        // send to back to LoginActivity
        Log.d(TAG, "Navigating to Login");
        Intent i = new Intent(SignupActivity.this, LoginActivity.class);
        startActivity(i);
        finish();   // finishes LoginActivity, takes it off the stack
        // when you press back, now exits app
        Log.d(TAG, "finished");
    }

}

