package com.example.be;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class RegisterActivity extends AppCompatActivity {

    private EditText name;
    private EditText email;
    private EditText password;
    private FirebaseAuth mAuth;
    private ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //toolBar
        Toolbar nToolbar = findViewById(R.id.reg_ToolBar);
        setSupportActionBar(nToolbar);
        getSupportActionBar().setTitle("Create Account");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        // Initialize FireBase Auth
        mAuth = FirebaseAuth.getInstance();
        name = findViewById(R.id.DisplayName);
        email = findViewById(R.id.Email);
        password = findViewById(R.id.Password);
        mProgressDialog = new ProgressDialog(this);
        Button createAcc = findViewById(R.id.Create_Account);

        createAcc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sDisplay_name = name.getText().toString();
                String sEmail = email.getText().toString();
                String sPassword = password.getText().toString();
                if(!TextUtils.isEmpty(sDisplay_name)||!TextUtils.isEmpty(sEmail)||!TextUtils.isEmpty(sPassword))
                {
                    mProgressDialog.setTitle("Setting Up Your Account");
                    mProgressDialog.setMessage("Please Wait");
                    mProgressDialog.setCanceledOnTouchOutside(false);
                    mProgressDialog.show();
                    regUser(sDisplay_name,sEmail,sPassword);
                }

            }
        });
    }

    private void regUser( String sDisplay_name, String Email, String Password) {

        mAuth.createUserWithEmailAndPassword(Email,Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful())
                {
                    Intent mainIntent = new Intent(RegisterActivity.this,MainActivity.class);
                    startActivity(mainIntent);
                    mProgressDialog.dismiss();
                    finish();
                }
                else
                {
                    Toast.makeText(RegisterActivity.this,"error",Toast.LENGTH_SHORT).show();
                    mProgressDialog.hide();

                }

            }
        });

    }
}
