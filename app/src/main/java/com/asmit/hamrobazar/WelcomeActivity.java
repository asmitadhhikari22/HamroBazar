package com.asmit.hamrobazar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

import androidx.appcompat.app.AppCompatActivity;

public class WelcomeActivity extends AppCompatActivity {
    CheckBox chk1,chk2,chk3;
    Button btnAgree;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        chk1=findViewById(R.id.chk1);
        chk2=findViewById(R.id.chk2);
        chk3=findViewById(R.id.chk3);
        btnAgree=findViewById(R.id.btnAgree);

        chk1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(WelcomeActivity.this,Checkbox1.class);
                startActivity(intent);
            }
        });
        chk2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(WelcomeActivity.this,Checkbox2.class);
                startActivity(intent);
            }
        });
        chk3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(WelcomeActivity.this,Checkbox3.class);
                startActivity(intent);
            }
        });
        btnAgree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(WelcomeActivity.this,DashboardActivity.class);
                startActivity(intent);
            }
        });


    }
}
