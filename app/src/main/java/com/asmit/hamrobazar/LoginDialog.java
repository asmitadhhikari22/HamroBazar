package com.asmit.hamrobazar;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatDialogFragment;

import com.asmit.hamrobazar.bll.LoginBLL;
import com.asmit.hamrobazar.strictmode.StrictModeClass;

public class LoginDialog extends AppCompatDialogFragment {

    private EditText etEmail, etPassword;


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.login_dialoge, null);


        builder.setView(view)
                .setTitle("Login")

                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(getContext(), DashboardActivity.class);
                        getContext().startActivity(intent);


                    }
                })

                .setPositiveButton("Login", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        if (TextUtils.isEmpty(etEmail.getText()))
                        {
                            etEmail.setError("Enter email address");
                            etEmail.requestFocus();
                            return;
                        }
                        if (TextUtils.isEmpty(etPassword.getText()))
                        {
                            etPassword.setError("Enter password");
                            etPassword.requestFocus();
                            return;
                        }

                        String email = etEmail.getText().toString();
                        String pwd = etPassword.getText().toString();

                        LoginBLL loginBLL = new LoginBLL();
                        StrictModeClass.StrictMode();

                        if (loginBLL.checkUser(email, pwd)) {
//
                            Toast.makeText(getActivity(), "Login Sucessfully", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getContext(), DashboardActivity.class);
                            getContext().startActivity(intent);

                        } else {
                            Toast.makeText(getActivity(), "Email or password donot match" + email + pwd, Toast.LENGTH_SHORT).show();
                        }


                    }
                })

                .setNeutralButton("Dont't have account? Register here", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(getContext(), RegisterActivity.class);
                        getContext().startActivity(intent);

                    }
                });


        etEmail = view.findViewById(R.id.etEmail);
        etPassword = view.findViewById(R.id.etPassword);

        return builder.create();

    }
}
