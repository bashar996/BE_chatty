package com.example.be;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Sign_in extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        // Initialize FireBase Auth
        mAuth = FirebaseAuth.getInstance();

        //ToolBar set up
        Toolbar mtoolBar = findViewById(R.id.signIn_ToolBar);
        setSupportActionBar(mtoolBar);
        getSupportActionBar().setTitle(R.string.sign_in);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Button sign_in = findViewById(R.id.SignIn_Btn);
        final EditText email = findViewById(R.id.Sign_Email);
        final EditText password = findViewById(R.id.Sign_Password);
        final String sEmail = email.getText().toString();
        final String sPassword = password.getText().toString();
        mProgressDialog = new ProgressDialog(this);

        sign_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!TextUtils.isEmpty(sEmail)||!TextUtils.isEmpty(sPassword))
                {
                    mProgressDialog.setMessage("Signing in");
                    mProgressDialog.setTitle("Please Wait");
                    mProgressDialog.show();
                    mProgressDialog.setCanceledOnTouchOutside(false);
                    signIn(sEmail,sPassword);
                }

            }
        });

    }

    private void signIn(String sEmail, String sPassword) {

        mAuth.signInWithEmailAndPassword(sEmail, sPassword)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information

                            Intent mainIntent = new Intent(Sign_in.this,MainActivity.class);
                            startActivity(mainIntent);
                            mProgressDialog.dismiss();
                            finish();

                        } else {

                            mProgressDialog.hide();
                            Toast.makeText(Sign_in.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();

                        }


                    }
                });
    }


}
