package com.example.sleepmood;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Activity_AlarmOff extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm_off);

        Button stop = (Button) findViewById(R.id.alarm_stop);

        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent alarmService = new Intent(Activity_AlarmOff.this, RingtonePlayingService.class);
//                alarmService.putExtra("command","alarm off");
//                alarmService.putExtra("sound","alarm.mp3");
//                startService(alarmService);
                stopService(alarmService);

                Intent intent = new Intent(Activity_AlarmOff.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }
}