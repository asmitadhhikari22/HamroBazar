package com.asmit.hamrobazar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Checkbox1 extends AppCompatActivity {

    Button backto;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkbox1);

        backto=findViewById(R.id.Backto);
        backto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(Checkbox1.this,WelcomeActivity.class);
                startActivity(intent);
            }
        });
    }
}
