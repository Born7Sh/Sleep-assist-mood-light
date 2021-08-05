package com.example.sleepmood;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    // 21-07-23 하단 네비게이션 변수
    BottomNavigationView bottomNavigationView;

    // 21-07-29 뒤로가기 버튼 클릭 / .addToBackStack(null) 추가
    private long backKeyPressedTime = 0;
    private Toast toast;

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
                                                                       getSupportFragmentManager().beginTransaction().replace(R.id.fragment_frame, new Fragment_Home()).addToBackStack(null).commit();
                                                                       break;
                                                                   case R.id.page_record:
                                                                       getSupportFragmentManager().beginTransaction().replace(R.id.fragment_frame, new Fragment_Record()).addToBackStack(null).commit();
                                                                       break;
                                                                   case R.id.page_light:
                                                                       getSupportFragmentManager().beginTransaction().replace(R.id.fragment_frame, new Fragment_light()).addToBackStack(null).commit();
                                                                       break;
                                                                   case R.id.page_setting:
                                                                       getSupportFragmentManager().beginTransaction().replace(R.id.fragment_frame, new Fragment_Setting()).addToBackStack(null).commit();
                                                                       break;


                                                               }
                                                               return true;
                                                           }
                                                       }
        );
    }

    public void onFragmentChange(String fragment) {
        if (fragment == "sleepReady") {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_frame, new Fragment_Home_SleepReady()).addToBackStack(null).commit();
        } else if (fragment == "sleepStart") {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_frame, new Fragment_Home_SleepStart()).addToBackStack(null).commit();
        } else if (fragment == "alarmAdd") {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_frame, new Fragment_Home_AlarmAdd()).addToBackStack(null).commit();
        } else if (fragment == "alarmList") {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_frame, new Fragment_Home_AlarmList()).addToBackStack(null).commit();
        } else if (fragment == "weatherInfo") {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_frame, new Fragment_Home_Weather()).addToBackStack(null).commit();
        } else if (fragment == "tema") {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_frame, new Fragment_Home_Tema()).addToBackStack(null).commit();
        }

    }

//    21-07-29 뒤로가기 버튼 추가
//    21-07-30 뒤고가기 기능은 되는데 home에서 두번 누르면 종료되도록 하고싶은데 이건 아직 구현안됨
//    @Override
//    public void onBackPressed() {
//
//        //super.onBackPressed();
//        // 기존 뒤로 가기 버튼의 기능을 막기 위해 주석 처리 또는 삭제
//
//        // 마지막으로 뒤로 가기 버튼을 눌렀던 시간에 2.5초를 더해 현재 시간과 비교 후
//        // 마지막으로 뒤로 가기 버튼을 눌렀던 시간이 2.5초가 지났으면 Toast 출력
//        // 2500 milliseconds = 2.5 seconds
//        if (System.currentTimeMillis() > backKeyPressedTime + 2500) {
//            backKeyPressedTime = System.currentTimeMillis();
//            toast = Toast.makeText(this, "뒤로 가기 버튼을 한 번 더 누르시면 종료됩니다.", Toast.LENGTH_LONG);
//            toast.show();
//            return;
//        }
//        // 마지막으로 뒤로 가기 버튼을 눌렀던 시간에 2.5초를 더해 현재 시간과 비교 후
//        // 마지막으로 뒤로 가기 버튼을 눌렀던 시간이 2.5초가 지나지 않았으면 종료
//        if (System.currentTimeMillis() <= backKeyPressedTime + 2500) {
//            finish();
//            toast.cancel();
//            toast = Toast.makeText(this,"이용해 주셔서 감사합니다.",Toast.LENGTH_LONG);
//            toast.show();
//        }
//    }

}