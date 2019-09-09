package com.example.be;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class start_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_);

        final Button signBtn = findViewById(R.id.start_sign_in_button);
        Button regBtn = findViewById(R.id.start_reg_button);

        regBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(start_Activity.this,RegisterActivity.class);

                startActivity(intent);
            }
        });

        signBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signIntent = new Intent(start_Activity.this,Sign_in.class);
                startActivity(signIntent);
            }
        });
    }
}
