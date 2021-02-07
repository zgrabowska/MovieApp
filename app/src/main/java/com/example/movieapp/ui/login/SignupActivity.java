package com.example.movieapp.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.movieapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignupActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private EditText email, password;
    private Button signUpButton;
    private static final String TAG = "SignUpActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        mAuth = FirebaseAuth.getInstance();

        email = findViewById(R.id.newEmail);
        password = findViewById(R.id.newPassword);
        signUpButton = findViewById(R.id.signUp);

        email.addTextChangedListener(signUpButtonListener);
        password.addTextChangedListener(signUpButtonListener);

        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("Signing Up!");
                SignUp();
            }
        });
    }
    //When initializing your Activity, check to see if the user is currently signed in:
//    @Override
//    public void onStart() {
//        super.onStart();
//        FirebaseUser currentUser = mAuth.getCurrentUser();
//        //updateUI(currentUser);
//    }

    private TextWatcher signUpButtonListener = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            signUpButton.setEnabled(email.getText().length() > 0 && password.getText().length() > 0);
        }

        @Override
        public void afterTextChanged(Editable s) {
        }
    };

    private void SignUp() {

        String newEmail, newPassword;
        newEmail = email.getText().toString();
        newPassword = password.getText().toString();

        mAuth.createUserWithEmailAndPassword(newEmail, newPassword)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {

                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(SignupActivity.this, "Registration successful!", Toast.LENGTH_LONG).show();
                            Log.d(TAG, "createUserWithEmail:success");

                            Intent goToHome = new Intent(SignupActivity.this, HomeActivity.class);
                            startActivity(goToHome);

                            //FirebaseUser user = mAuth.getCurrentUser();

                        } else {
                            Toast.makeText(SignupActivity.this, "Registration failed!!", Toast.LENGTH_LONG).show();
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                        }
                    }
                });

    }

}
