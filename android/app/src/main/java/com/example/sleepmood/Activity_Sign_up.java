package com.example.sleepmood;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Activity_Sign_up extends AppCompatActivity {

    String a; // 활성화된거 몇개인지 확인하는 것
    int countEnable = 0; // 활성화된게 몇개있지 확인
    int countIsCorrect = 0; // 글자가 들어가 있는지 확인

    private Button btn_Signup_Finish;
    private EditText signup_Id;
    private EditText signup_Pwd;
    private EditText signup_Pwd_Check;


    private EditText signup_Phone;
    private EditText signup_Job;
    private EditText signup_Birth;
    private EditText signup_Address;
    private EditText signup_Email;


    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        signup_Id = findViewById(R.id.signup_Id);
        signup_Pwd = findViewById(R.id.signup_Pwd);
        signup_Pwd_Check = findViewById(R.id.signup_Pwd_Check);


        signup_Phone = findViewById(R.id.signup_Phone);
        signup_Job = findViewById(R.id.signup_Job);
        signup_Birth = findViewById(R.id.signup_Birth);
        signup_Address = findViewById(R.id.signup_Address);
        signup_Email = findViewById(R.id.signup_Email);

        btn_Signup_Finish = findViewById(R.id.btn_Signup_Finish);

//        checkIsEnable(signup_Id);
//        checkIsEnable(signup_Pwd);
//        checkIsEnable(signup_Pwd_Check);
//        checkIsEnable(signup_Phone);
//        checkIsEnable(signup_Job);
//        checkIsEnable(signup_Birth);

        btn_Signup_Finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkIsPwd();
            }
        });

    }

      void checkIsPwd(){ // 비번 설정
        if(!signup_Pwd.getText().toString().equals(signup_Pwd_Check.getText().toString())){
            Toast.makeText(getApplicationContext(), "비번 불일치! ", Toast.LENGTH_LONG).show();
        } else {
            Log.v("알림", "일단 비번 설정까지는 들어옴");
            callFunction();
        }
      }

      void callFunction(){ // call function
//          UserData ud = new UserData(signup_Id.getText().toString(),signup_Email.getText().toString(),signup_Address.getText().toString(),signup_Job.getText().toString(),signup_Phone.getText().toString(),signup_Pwd.getText().toString(),signup_Birth.getText().toString());


          RetroBuilder retro = new RetroBuilder();
           UserData ud = new UserData("homing12@naver.com","경기도" ,"학생", "010-1234-1243", "pwde1234", "1997-12-12");
          //  Call<DiaryData2> call = retro.service.provideDiaryDay("json","born7sh@gmail.com","2021-08-26","posttestright?");
//        Call<DiaryData> call = retro.service.provideDiaryDay(d2,"Bearer " +checkFirst);
          Call<UserData> call = retro.service.provideUserData(ud);

          call.enqueue(new Callback<UserData>() {
                           @Override
                           public void onResponse(Call<UserData> call, Response<UserData> response) {
                               Log.v("알림", "확인");
                               if (response.isSuccessful()) {
                                   Log.v("알림", "성공1");
                               }
                               moveToLogin();
                           }

                           @Override
                           public void onFailure(Call<UserData> call, Throwable t) {
                               Log.v("알림", "실패1");
                               callFunction();
                           }
                       }
          );

      }

      void moveToLogin(){ // 이동
          Log.v("알림", "로그인화면으로 이동");
          Intent intent = new Intent(Activity_Sign_up.this, Activity_Log_in.class);
          startActivity(intent);
          finish();
    }



//    void checkIsEnable(EditText sign) {
//
//        sign.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                a = sign.getText().toString();
//
//                if (a.length() > 0) {
//                    countEnable = 1;
//                } else {
//                    countEnable = 0;
//                }
//
//
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//                a = sign.getText().toString();
//
//                if (countEnable == 1) {
//                    countIsCorrect = countIsCorrect + 1;
//                }
//                if (countIsCorrect == 6) {
//                    btn_Signup_Finish.setEnabled(true);
//                }
//            }
//        });
//
//
//    }
}