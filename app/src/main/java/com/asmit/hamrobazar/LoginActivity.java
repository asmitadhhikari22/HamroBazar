package com.asmit.hamrobazar;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

  /*  Button btnSignUp;
    EditText etLEmail,etPassword;
    Button btnLogin,btnForget;*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

     /*   btnSignUp=findViewById(R.id.btnSignUp);
        btnForget=findViewById(R.id.btnForget);
        btnLogin=findViewById(R.id.btnLogin);
        etLEmail=findViewById(R.id.etLEmail);
        etPassword=findViewById(R.id.etPassword);

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
            }
        });
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });
    }

    private void login() {
        String email=etLEmail.getText().toString();
        String password=etPassword.getText().toString();
        LoginBLL loginBLL = new LoginBLL();

        StrictModeClass.StrictMode();
        if (loginBLL.checkUser(email, password)) {
            Intent intent = new Intent(LoginActivity.this, DashboardActivity.class);
            startActivity(intent);
            finish();
        } else {
            Toast.makeText(this, "Either email or password is incorrect", Toast.LENGTH_SHORT).show();
            etLEmail.requestFocus();
        }
    }*/
    }
}
