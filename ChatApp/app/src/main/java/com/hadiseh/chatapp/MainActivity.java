package com.hadiseh.chatapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    private EditText edtUsername, edtPassword, edtEmail;
    private Button btnSubmit;
    private TextView txtLoginInfo;

    private boolean isSigningUp = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        edtEmail = findViewById(R.id.edtEmail);
        edtPassword = findViewById(R.id.edtPassword);
        edtUsername = findViewById(R.id.edtUsername);

        btnSubmit = findViewById(R.id.btnSubmit);

        txtLoginInfo = findViewById(R.id.txtLoginInfo);
        txtLoginInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isSigningUp){
                    isSigningUp = false;
                    edtUsername.setVisibility(View.GONE);
                    btnSubmit.setText("Log in");
                    txtLoginInfo.setText("Don't have an account? Sign up");
                }else {
                    isSigningUp = true;
                    edtUsername.setVisibility(View.VISIBLE);
                    btnSubmit.setText("Sign up");
                    txtLoginInfo.setText("Already have an account? Log in");
                }

            }
        });

        };
    }
