package com.triodev.a24hoursapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {


    private EditText inputeMail, inputPass;
    private FirebaseAuth auth;
    private Button btnLogin, btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        auth = FirebaseAuth.getInstance();

        if (auth.getCurrentUser() != null) {
            //uye girisi yapilinca calisacak yer

            finish();
        }

        inputeMail = findViewById(R.id.inputMail);
        inputPass = findViewById(R.id.inputPass);
        btnLogin = findViewById(R.id.loginButton);
        btnRegister = findViewById(R.id.logtoreg);


        auth = FirebaseAuth.getInstance();

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent register = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(register);
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = inputeMail.getText().toString();
                final String password = inputPass.getText().toString();


                if (TextUtils.isEmpty(email)) {

                    Toast.makeText(getApplicationContext(), "Mail Adresinizi Yazin", Toast.LENGTH_LONG);
                    return;

                }

                if (TextUtils.isEmpty(password)) {

                    Toast.makeText(getApplicationContext(), "Lütfen Şifrenizi Yazın", Toast.LENGTH_LONG);
                    return;


                }

                auth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {


                                if (!task.isSuccessful()) {
                                    // there was an error
                                    if (password.length() < 6) {
                                        inputPass.setError("Sifre Cok Kisa");
                                    } else {
                                        Toast.makeText(MainActivity.this, "Giris Hatali", Toast.LENGTH_LONG).show();
                                    }
                                } else {
                                    Intent intent = new Intent(MainActivity.this, MainActivity.class);
                                    startActivity(intent);
                                    finish();
                                }
                            }
                        });
            }
        });
    }
}