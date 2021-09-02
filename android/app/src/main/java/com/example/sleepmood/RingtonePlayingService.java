package com.example.sleepmood;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;

import java.util.ArrayList;

public class RingtonePlayingService extends Service {

    MediaPlayer mediaPlayer;
    int startId;
    boolean isRunning;
    Intent goAlarmOff;


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        goAlarmOff = new Intent(getApplicationContext(), MainActivity.class);

        if (Build.VERSION.SDK_INT >= 26) {
            String CHANNEL_ID = "default";
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID,
                    "Channel human readable title",
                    NotificationManager.IMPORTANCE_DEFAULT);

            ((NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE)).createNotificationChannel(channel);

            Notification notification =
                    new NotificationCompat.Builder(this, CHANNEL_ID)
                    .setContentTitle("알람시작")
                    .setContentText("알람음이 재생됩니다.")
                    .setSmallIcon(R.mipmap.ic_launcher)

                    .build();

            startForeground(1, notification);


        }
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (intent == null) { // 인테트가 null이면,
            return Service.START_NOT_STICKY; // 서비스가 종료 되었을 때도 다시 자동으로 실행 함.
        } else {
            processCommand(intent);
        }
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        mediaPlayer.stop();
        mediaPlayer.reset();
        mediaPlayer.release();

        this.isRunning = false;
        this.startId = 0;

        super.onDestroy();
        Log.d("onDestory() 실행", "서비스 파괴");

    }

    private void processCommand(Intent intent) {

        String getState = intent.getExtras().getString("state");
        int getRequestCode = intent.getExtras().getInt("requestCode");
        createNotificationMessage("알람 시작"
                , "알람음 재생중...");

        goAlarmOff.putExtra("command", "show");

        assert getState != null;
        switch (getState) {
            case "alarm on":
                startId = 1;
                break;
            case "alarm off":
                startId = 0;
                break;
            default:
                startId = 0;
                break;
        }

        // 알람음 재생 X , 알람음 시작 클릭
        if (!this.isRunning && startId == 1) {

            mediaPlayer = MediaPlayer.create(this, R.raw.lemon);
            mediaPlayer.start();

            this.isRunning = true;
            this.startId = 0;

            goAlarmOff.putExtra("command", "show");
            goAlarmOff.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK |
                    Intent.FLAG_ACTIVITY_SINGLE_TOP |
                    Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(goAlarmOff);

//            Intent notificationIntent = new Intent(this, MainActivity.class);
//            notificationIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
//                    | Intent.FLAG_ACTIVITY_SINGLE_TOP
//                    | Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);
//            PendingIntent pendingIntent = PendingIntent.getActivity(this, 200, notificationIntent, PendingIntent.FLAG_CANCEL_CURRENT);
//            try {
//                pendingIntent.send();
//            } catch (PendingIntent.CanceledException e) {
//                e.printStackTrace();
//            }
        }

        // 알람음 재생 O , 알람음 종료 버튼 클릭
        else if (this.isRunning && startId == 0) {

            mediaPlayer.stop();
            mediaPlayer.reset();
            mediaPlayer.release();

            this.isRunning = false;
            this.startId = 0;
        }

        // 알람음 재생 X , 알람음 종료 버튼 클릭
        else if (!this.isRunning && startId == 0) {

            this.isRunning = false;
            this.startId = 0;

        }

        // 알람음 재생 O , 알람음 시작 버튼 클릭
        else if (this.isRunning && startId == 1) {

            this.isRunning = true;
            this.startId = 1;
        } else {
        }


    }


    private void createNotificationMessage(String channelName, String description) {
        Intent notificationIntent = new Intent(this, Activity_AlarmOff.class);
        notificationIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
                | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        PendingIntent pendingIntent =
                PendingIntent.getActivity(this, 200,
                        notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationManager notificationManager =
                (NotificationManager) this.getSystemService(Context.NOTIFICATION_SERVICE);
        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(this, "alarm");

        String chanelId = "alarm";

        //OREO API 26 이상에서는 채널 필요
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {

            builder.setSmallIcon(R.drawable.ic_launcher_foreground); //mipmap 사용시 Oreo 이상에서 시스템 UI 에러남


            int importance = NotificationManager.IMPORTANCE_HIGH; // 소리와 알림메시지를 같이 보여줌

            NotificationChannel channel = new NotificationChannel(chanelId, channelName, importance);
            channel.setDescription(description);

            // 노티피케이션 채널을 시스템에 등록
            if (notificationManager != null) {
                notificationManager.createNotificationChannel(channel);
            }
        } else
            builder.setSmallIcon(R.mipmap.ic_launcher);

        builder.setAutoCancel(true)
                .setDefaults(NotificationCompat.DEFAULT_ALL)
                .setWhen(System.currentTimeMillis())
                .setTicker("{Time to watch some cool stuff!}")
                .setContentTitle(channelName)
                .setContentText(description)
                .setContentInfo("INFO")
                .setContentIntent(pendingIntent);


        if (notificationManager != null) {
//            Intent alarmIntent = new Intent("android.intent.action.sec");
//
//            alarmIntent.setClass(context, AlarmOffActivity.class);
//            alarmIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK |
//                    Intent.FLAG_ACTIVITY_SINGLE_TOP |
//                    Intent.FLAG_ACTIVITY_CLEAR_TOP);

            startForeground(200, builder.build());
            notificationManager.notify(200, builder.build());

            // 액티비티를 띄운다
//            context.startActivity(alarmIntent);

        }
    }
}