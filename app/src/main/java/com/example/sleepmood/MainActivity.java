package com.example.sleepmood;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    // 21-07-23 하단 네비게이션 변수
    BottomNavigationView bottomNavigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //21-07-22 앱최초 실행 작업
        SharedPreferences pref = getSharedPreferences("checkFirst", Activity.MODE_PRIVATE);
        boolean checkFirst = pref.getBoolean("checkFirst", false);

        if (checkFirst == false) {
            // @######구글 로그인 세션까지 조건 작업 할것######@
            // 앱 최초 실행시 하고 싶은 작업
            SharedPreferences.Editor editor = pref.edit();
            editor.putBoolean("checkFirst", true);
            editor.commit();

            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        } else {
            // 최초 실행이 아닐때 진행할 작업
        }

        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        getSupportFragmentManager().beginTransaction().add(R.id.fragment_frame, new Fragment_Home()).commit();
        bottomNavigationView.setOnItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) { //item을 클릭시 id값을 가져와 FrameLayout에 fragment.xml띄우기
                    case R.id.page_home:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_frame, new Fragment_Home()).commit();
                        break;
                    case R.id.page_record:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_frame, new Fragment_Record()).commit();
                        break;
                    case R.id.page_light:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_frame, new Fragment_light()).commit();
                        break;
                    case R.id.page_setting:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_frame, new Fragment_Setting()).commit();
                        break;


                    }
                return true;
            }
        }
    );


    }
}