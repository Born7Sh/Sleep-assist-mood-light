package com.example.sleepmood;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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

//                LoginData ld = new LoginData("inho216","pw1");
                LoginData ld = new LoginData(login_Id.getText().toString(), login_pwd.getText().toString());
                RetroBuilder retro = new RetroBuilder();
                Call<String> call = retro.service.tryLogin(ld);
                call.enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        if (response.isSuccessful()) {
                            String token = response.body();
                            SharedPreferences pref = getSharedPreferences("token", Activity.MODE_PRIVATE);
                            String checkFirst = pref.getString("token", token);
                            SharedPreferences.Editor editor = pref.edit();
                            editor.putString("token", token);
                            editor.commit();


                            SharedPreferences pref2 = getSharedPreferences("id", Activity.MODE_PRIVATE);
                            String checkFirst2 = pref2.getString("id", token);
                            SharedPreferences.Editor editor2 = pref2.edit();
                            editor2.putString("id", login_Id.getText().toString());
                            editor2.commit();

                            SharedPreferences pref3 = getSharedPreferences("pw", Activity.MODE_PRIVATE);
                            String checkFirst3 = pref3.getString("pw", token);
                            Log.v("알림", "비번 : " + checkFirst3);
                            SharedPreferences.Editor editor3 = pref3.edit();
                            editor3.putString("pw", login_pwd.getText().toString());
                            editor3.commit();


                            if (token == "NULL") {
                                Log.v("알림", "틀림");
                                Toast.makeText(getApplicationContext(), "로그인 실패!!!", Toast.LENGTH_LONG).show();
                            }
                        }
                        Intent intent = new Intent(Activity_Log_in.this, MainActivity.class);
                        startActivity(intent);
                        finish();

                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        Log.v("알림", "일단 비번 설정까지는 들어옴");
                    }
                });


//                if(login_Id.getText().toString().equals("1")  || login_pwd.getText().toString().equals("1")){
//
//                    SharedPreferences pref = getSharedPreferences("checkFirst", Activity.MODE_PRIVATE);
//                    boolean checkFirst = pref.getBoolean("checkFirst", false);
//
//                    SharedPreferences.Editor editor = pref.edit();
//                    editor.putBoolean("checkFirst", true);
//                    editor.commit();
//
//                    Intent intent = new Intent(Activity_Log_in.this, MainActivity.class);
//                    startActivity(intent);
//                    finish();
//
//                }
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