package com.example.movieapp.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;

import com.example.movieapp.R;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private EditText username, password;
    private Button signInButton, signUpButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        signInButton = findViewById(R.id.signIn);
        signUpButton = findViewById(R.id.signUp);

        username.addTextChangedListener(logInButtonListener);
        password.addTextChangedListener(logInButtonListener);

        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //SignIn();
            }
        });

        signUpButton.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToSignUp = new Intent(LoginActivity.this, SignupActivity.class);
                startActivity(goToSignUp);
            }
        }));

    }

    private TextWatcher logInButtonListener = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            signInButton.setEnabled(username.getText().length() > 0 && password.getText().length() > 0);
        }

        @Override
        public void afterTextChanged(Editable s) {
        }
    };

    private void SignIn() {

        String emailCheck, passwordCheck;
        emailCheck = username.getText().toString();
        passwordCheck = password.getText().toString();
        System.out.println(emailCheck);
        System.out.println(passwordCheck);
    }
}