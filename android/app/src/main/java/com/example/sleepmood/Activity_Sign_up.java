package com.example.sleepmood;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;

public class Activity_Sign_up extends AppCompatActivity {

    String a; // 활성화된거 몇개인지 확인하는 것
    int countEnable = 0; // 활성화된게 몇개있지 확인
    int countIsCorrect = 0; // 글자가 들어가 있는지 확인

    private Button btn_Signup_Finish;


    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        EditText signup_Id = findViewById(R.id.signup_Id);
        EditText signup_Pwd = findViewById(R.id.signup_Pwd);
        EditText signup_Pwd_Check = findViewById(R.id.signup_Pwd_Check);
        EditText signup_Phone = findViewById(R.id.signup_Phone);
        EditText signup_Job = findViewById(R.id.signup_Job);
        EditText signup_Birth = findViewById(R.id.signup_Birth);

        btn_Signup_Finish = findViewById(R.id.btn_Signup_Finish);

//        checkIsEnable(signup_Id);
//        checkIsEnable(signup_Pwd);
//        checkIsEnable(signup_Pwd_Check);
//        checkIsEnable(signup_Phone);
//        checkIsEnable(signup_Job);
//        checkIsEnable(signup_Birth);



    }

    void checkIsEnable(EditText sign){

        sign.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                a = sign.getText().toString();

                if(a.length() > 0){
                    countEnable = 1;
                }else {
                    countEnable = 0;
                }



            }

            @Override
            public void afterTextChanged(Editable s) {
                a = sign.getText().toString();

                if(countEnable == 1){
                    countIsCorrect = countIsCorrect+1;
                }
                if(countIsCorrect == 6){
                    btn_Signup_Finish.setEnabled(true);
                }
            }
        });


    }
}