package com.example.sleepmood;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    Context mainContext;
    Activity mainActivity;
    // 21-07-23 하단 네비게이션 변수
    BottomNavigationView bottomNavigationView;
    public static int ACTION_MANAGE_OVERLAY_PERMISSION_REQUEST_CODE = 2323;


    // 21-07-29 뒤로가기 버튼 클릭 / .addToBackStack(null) 추가
    private long backKeyPressedTime = 0;
    private int AlarmNum = -1;
    private Toast toast;

    Fragment_Home_AlarmList fragment_home_alarmList = new Fragment_Home_AlarmList(); // 새로고침용
    FragmentTransaction fragmentTransaction; // 새로고침용 변수

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainContext = getApplicationContext();
        mainActivity = MainActivity.this;

//if the user already granted the permission or the API is below Android 10 no need to ask for permission

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q &&
                !Settings.canDrawOverlays(mainContext)) {
            RequestPermission();
        }


//        //21-07-22 앱최초 실행 작업
//        SharedPreferences pref = getSharedPreferences("checkFirst", Activity.MODE_PRIVATE);
//        boolean checkFirst = pref.getBoolean("checkFirst", false);
        SharedPreferences pref = getSharedPreferences("token", Activity.MODE_PRIVATE);
        String checkFirst = pref.getString("token","NULL");

        Intent passedIntent = getIntent();
        processCommand(passedIntent);

        if (checkFirst == "NULL") {
            // @######구글 로그인 세션까지 조건 작업 할것######@
            // 앱 최초 실행시 하고 싶은 작업
//            SharedPreferences.Editor editor = pref.edit();
//            editor.putBoolean("checkFirst", true);
//            editor.commit();
            Intent intent = new Intent(MainActivity.this, Activity_Log_in.class);
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
                                                                       getSupportFragmentManager().beginTransaction().replace(R.id.fragment_frame, new Fragment_Light()).addToBackStack(null).commit();
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
            int a = AlarmNum;
            Fragment_Home_AlarmAdd fragment1 = new Fragment_Home_AlarmAdd();
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_frame, fragment1).addToBackStack(null).commit();
            // 데이터 보내기
            Bundle bundle = new Bundle();
            bundle.putInt("AlarmNum", a); //fragment1로 번들 전달
            fragment1.setArguments(bundle);
            AlarmNum = -1;

        } else if (fragment == "alarmList" || fragment == "refresh") {
            if (fragment == "alarmList") {
                fragment_home_alarmList = new Fragment_Home_AlarmList();
                fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.fragment_frame, fragment_home_alarmList);
                fragmentTransaction.commit();
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_frame, fragment_home_alarmList).addToBackStack(null).commit();
            } else{
                fragment_home_alarmList.getParentFragmentManager().beginTransaction().detach(fragment_home_alarmList).commit();
                fragment_home_alarmList.getParentFragmentManager().beginTransaction().attach(fragment_home_alarmList).commit();
//                fragmentTransaction.detach(fragment_home_alarmList);
//                fragmentTransaction.attach(fragment_home_alarmList).commit();

  }
        } else if (fragment == "weatherInfo") {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_frame, new Fragment_Home_Weather()).addToBackStack(null).commit();
        } else if (fragment == "tema") {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_frame, new Fragment_Home_Tema()).addToBackStack(null).commit();
        } else if (fragment == "setting_User_Edit") {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_frame, new Fragment_UserInfo_Edit()).addToBackStack(null).commit();
        } else if (fragment == "setting_User_Drop") {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_frame, new Fragment_UserInfo_Drop()).addToBackStack(null).commit();
        } else if (fragment == "setting_AppInfo") {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_frame, new Fragment_Setting_AppInfo()).addToBackStack(null).commit();
        } else if (fragment == "setting_Help") {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_frame, new Fragment_Setting_Help()).addToBackStack(null).commit();
        } else if (fragment == "setting_Question") {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_frame, new Fragment_Setting_Question()).addToBackStack(null).commit();
        } else if (fragment == "setting_Cycle") {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_frame, new Fragment_Setting_SleepCycle()).addToBackStack(null).commit();
        }

    }

    public void setItemNumber(int AlarmNum) {
        this.AlarmNum = AlarmNum;
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


    @Override
    protected void onNewIntent(Intent intent) {
        processCommand(intent);
        super.onNewIntent(intent);
    }

    private void processCommand(Intent intent) {
        if (intent != null) {
            String command = intent.getStringExtra("command");

            if ("show".equals(command)) {
                startActivity(new Intent(this, Activity_AlarmOff.class));
            }
        }

    }

    private void RequestPermission() {
        // Check if Android M or higher
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            // Show alert dialog to the user saying a separate permission is needed
            // Launch the settings activity if the user prefers
            Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                    Uri.parse("package:" + mainActivity.getPackageName()));
            startActivityForResult(intent, ACTION_MANAGE_OVERLAY_PERMISSION_REQUEST_CODE);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == ACTION_MANAGE_OVERLAY_PERMISSION_REQUEST_CODE) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (!Settings.canDrawOverlays(mainContext)) {
                    Toast.makeText(MainActivity.this, "Failed", Toast.LENGTH_SHORT).show();
                } else {
                    // Permission Granted-System will work
                }

            }
        }
    }


}