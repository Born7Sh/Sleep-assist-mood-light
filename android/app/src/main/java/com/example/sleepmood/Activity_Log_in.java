package com.example.sleepmood;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Activity_Log_in extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        EditText login_Id = findViewById(R.id.login_Id);
        EditText login_pwd = findViewById(R.id.login_pwd);

        Button btn_Login = findViewById(R.id.btn_Login);
        Button btn_Signup = findViewById(R.id.btn_Signup);


        btn_Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // login_Id 랑 login_pwd 가져와서 로그인 하면 됨.

                if(login_Id.getText().toString().equals("1")  || login_pwd.getText().toString().equals("1")){

                    SharedPreferences pref = getSharedPreferences("checkFirst", Activity.MODE_PRIVATE);
                    boolean checkFirst = pref.getBoolean("checkFirst", false);

                    SharedPreferences.Editor editor = pref.edit();
                    editor.putBoolean("checkFirst", true);
                    editor.commit();

                    Intent intent = new Intent(Activity_Log_in.this, MainActivity.class);
                    startActivity(intent);
                    finish();

                }
            }
        });
        btn_Signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Activity_Log_in.this, Activity_Sign_up.class);
                startActivity(intent);
                finish();
            }
        });
    }
}