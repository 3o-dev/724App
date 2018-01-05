package com.triodev.a24hoursapp;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.TextureView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {

    private EditText regmail, regpass;
    private Button regok, gotologin;
    private  FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        regmail = findViewById(R.id.inputMail);
        regpass = findViewById(R.id.inputPass);
        regok = findViewById(R.id.regok);
        gotologin = findViewById(R.id.gologin);


        auth = FirebaseAuth.getInstance();


        regok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = regmail.getText().toString();
                String password = regpass.getText().toString().trim();

                if(TextUtils.isEmpty(email)){

                    Toast.makeText(getApplicationContext(), "email yaziniz", Toast.LENGTH_LONG);
                    return;

                }

                if(TextUtils.isEmpty(password)){
                    Toast.makeText(getApplicationContext(), "Sifre bos olamaz", Toast.LENGTH_LONG);

                    return;
                }
                if(password.length() < 6){

                Toast.makeText(getApplicationContext(), "Sifre Cok Kisa", Toast.LENGTH_LONG);
                    return;

                }

                auth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                Toast.makeText(RegisterActivity.this, "createUserWithEmail:onComplete:" + task.isSuccessful(), Toast.LENGTH_SHORT).show();

                                // If sign in fails, display a message to the user. If sign in succeeds
                                // the auth state listener will be notified and logic to handle the
                                // signed in user can be handled in the listener.
                                if (!task.isSuccessful()) {
                                    Toast.makeText(RegisterActivity.this, "Authentication failed." + task.getException(),
                                            Toast.LENGTH_SHORT).show();
                                } else {
                                    //GIRIS YAPILAN ACTIVITYE YONLENDIRME
//                                    startActivity(new Intent(RegisterActivity.this, MainActivity.class));
//                                    finish();
                                }
                            }
                        });



            }
        });


    }




}
