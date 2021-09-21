package com.example.sleepmood;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.jetbrains.annotations.NotNull;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_Home_SleepStart extends Fragment {

    private TextView tv_roll, tv_pitch;
    private TextView tv_grade;

    private TextView tv_timeNow, tv_timeAfter;

    private SensorManager mSensorManager = null;
    private UserSensorListner userSensorListner;
    private Sensor mGyroscopeSensor = null;
    private Sensor mAccelerometer = null;

    private int waterC = 0;
    private int awakeC = 0;
    private int toiletC = 0;

    private int grade;

    private int id; // 멈추기 위한 id

    // 시간을 분 단위로 계산
    private long timeNow;
    private long timeAfter;
    private long sleepingTime;
    private String dateNow;

    /*Sensor variables*/
    private float[] mGyroValues = new float[3];
    private float[] mAccValues = new float[3];
    private double mAccPitch, mAccRoll;

    /*for unsing complementary fliter*/
    private float a = 0.2f;
    private static final float NS2S = 1.0f / 1000000000.0f;
    private double pitch = 0, roll = 0;
    private double timestamp;
    private double dt;
    private double temp;
    private boolean running;
    private boolean gyroRunning;
    private boolean accRunning;

    private SharedPreferences pref;
    private String checkFirst;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment__home__sleep_start, container, false);
        return rootView;
    }


    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        pref = getActivity().getSharedPreferences("token", Activity.MODE_PRIVATE);
        checkFirst = pref.getString("token", "NULL");


        tv_timeAfter = (TextView) view.findViewById(R.id.timeAfter);
        tv_timeNow = (TextView) view.findViewById(R.id.timeAfter);

        Date curDate = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmm");
        //요청시간을 Date로 parsing 후 time가져오기
        try {
            curDate = dateFormat.parse(dateFormat.format(curDate));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        timeNow = curDate.getTime();

        tv_timeAfter.setText(Long.toString(timeNow));

        /// 지금은 여기서 10초에 한번씩 실행
        Timer timer = new Timer();
        TimerTask TT = new TimerTask() {
            @Override
            public void run() {
                callStatus();
            }
        };

        timer.schedule(TT, 0, 100000); //Timer 실행
//        timer.cancel();//타이머 종료



//        Timer timer2 = new Timer();
//
//        TimerTask TT2 = new TimerTask() {
//            @Override
//            public void run() {
//                // 반복실행할 구문
//                Log.v("알림", String.valueOf(pitch));
//                Log.v("알림", String.valueOf(roll));
//
//            }
//
//
//        };
//
//        timer2.schedule(TT2, 0, 1000); //Timer 실행

        tv_roll = (TextView) view.findViewById(R.id.tv_roll);
        tv_pitch = (TextView) view.findViewById(R.id.tv_pitch);
        tv_grade = (TextView) view.findViewById(R.id.tv_grade);

        mSensorManager = (SensorManager) getActivity().getSystemService(Context.SENSOR_SERVICE);
        userSensorListner = new UserSensorListner();
        mGyroscopeSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
        mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        if (!running) {
            running = true;
            mSensorManager.registerListener(userSensorListner, mGyroscopeSensor, SensorManager.SENSOR_DELAY_UI);
            mSensorManager.registerListener(userSensorListner, mAccelerometer, SensorManager.SENSOR_DELAY_UI);
        } else if (running) {
            running = false;
            mSensorManager.unregisterListener(userSensorListner);

        }

        ImageView water = (ImageView) view.findViewById(R.id.water);
        ImageView awake = (ImageView) view.findViewById(R.id.awake);
        ImageView toilet = (ImageView) view.findViewById(R.id.toilet);

        TextView waterCount = (TextView) view.findViewById(R.id.waterCount);
        TextView awakeCount = (TextView) view.findViewById(R.id.awakeCount);
        TextView toiletCount = (TextView) view.findViewById(R.id.toiletCount);

        Button sleep_Stop = (Button) view.findViewById(R.id.sleep_Stop);

        water.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                waterC++;
                waterCount.setText(String.valueOf(waterC));
            }
        });

        awake.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                awakeC++;
                awakeCount.setText(String.valueOf(awakeC));

            }
        });

        toilet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toiletC++;
                toiletCount.setText(String.valueOf(toiletC));

            }
        });

        sleep_Stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                callUpdate();

//                grade = waterC + awakeC + toiletC + grade; // 점수
//                tv_grade.setText("grade : " + grade);
//
//                Date curDate2 = new Date();
//                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmm"); // 시간 차이 용
//                SimpleDateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd"); // 날짜 구하는 용
//
//                //요청시간을 Date로 parsing 후 time가져오기
//                try {
//                    curDate2 = dateFormat.parse(dateFormat.format(curDate2));
//
//                } catch (ParseException e) {
//                    e.printStackTrace();
//                }
//
//                timeAfter = curDate2.getTime(); // 분 차이
//                tv_timeAfter.setText(Long.toString((timeAfter - timeNow) / 60000));
//                dateNow = dateFormat2.format(curDate2); // 현재 날짜
//                Toast.makeText(getContext(), dateNow, Toast.LENGTH_SHORT).show();
//


            }
        });

        callSleep();


    }

    private void complementaty(double new_ts) {
        /* 자이로랑 가속 해제 */
        gyroRunning = false;
        accRunning = false;

        /*센서 값 첫 출력시 dt(=timestamp - event.timestamp)에 오차가 생기므로 처음엔 break */
        if (timestamp == 0) {
            timestamp = new_ts;
            return;
        }
        dt = (new_ts - timestamp) * NS2S; // ns->s 변환
        timestamp = new_ts;

        /* degree measure for accelerometer */
        mAccPitch = -Math.atan2(mAccValues[0], mAccValues[2]) * 180.0 / Math.PI; // Y 축 기준
        mAccRoll = Math.atan2(mAccValues[1], mAccValues[2]) * 180.0 / Math.PI; // X 축 기준

        /**
         * 1st complementary filter.
         *  mGyroValuess : 각속도 성분.
         *  mAccPitch : 가속도계를 통해 얻어낸 회전각.
         */
        temp = (1 / a) * (mAccPitch - pitch) + mGyroValues[1];
        pitch = pitch + (temp * dt);

        temp = (1 / a) * (mAccRoll - roll) + mGyroValues[0];
        roll = roll + (temp * dt);

        tv_roll.setText("roll : " + roll);
        tv_pitch.setText("pitch : " + pitch);


    }

    public class UserSensorListner implements SensorEventListener {

        @Override
        public void onSensorChanged(SensorEvent event) {
            switch (event.sensor.getType()) {

                /** 자이로 센서 */
                case Sensor.TYPE_GYROSCOPE:

                    /*센서 값을 mGyroValues에 저장*/
                    mGyroValues = event.values;

                    if (!gyroRunning)
                        gyroRunning = true;

                    break;

                /** 가속도 센서 */
                case Sensor.TYPE_ACCELEROMETER:

                    /*센서 값을 mAccValues에 저장*/
                    mAccValues = event.values;

                    if (!accRunning)
                        accRunning = true;

                    break;

            }

            /**두 센서 새로운 값을 받으면 상보필터 적용*/
            if (gyroRunning && accRunning) {

                if (roll > 5) {
                    grade++;
                }

                if ((int) Math.round(roll) > 5) {
                    grade++;
                }
//                if(roll>5 && pitch>5){
//                    grade = grade + 5;
//                }
                complementaty(event.timestamp);
            }

        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {
        }
    }

    public void callSleep() {

        Date curDate = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); // 시간 차이 용

        RetroBuilder retro = new RetroBuilder();
        SleepTime st = new SleepTime(dateFormat.format(curDate), 1, "1");
        Call<Integer> call = retro.service.provideSleepTime(st, "Bearer " + checkFirst);

        call.enqueue(new Callback<Integer>() {
                         @Override
                         public void onResponse(Call<Integer> call, Response<Integer> response) {
                             Log.v("알림", "callSleep 돌입");
                             if (response.isSuccessful()) {
                                 Log.v("알림", "callSleep 돌입 값 받아오기 성공");
                             }
                             id = response.body();
                             Log.v("알림", "id는 : "+ id);

                         }

                         @Override
                         public void onFailure(Call<Integer> call, Throwable t) {
                             Log.v("알림", "callSleep 실패");
                         }
                     }
        );
    }

    public void callStatus() {
        RetroBuilder retro = new RetroBuilder();
        float a = (float) pitch;
        float b = (float) roll;

        Date curDate = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");
        SleepData d2 = new SleepData(a, b, 4, id, dateFormat.format(curDate));
        Call<SleepData> call2 = retro.service.provideSleepData(d2,"Bearer " + checkFirst);

        call2.enqueue(new Callback<SleepData>() {
                         @Override
                         public void onResponse(Call<SleepData> call, Response<SleepData> response) {
                             Log.v("알림", "callStatus 돌입");
                             if (response.isSuccessful()) {
                                 Log.v("알림", "값 받아오기 성공");
                             }
                             Log.v("알림", "roll : " + a + "/ pitch : " + b);
                         }

                         @Override
                         public void onFailure(Call<SleepData> call, Throwable t) {
                             Log.v("알림", "callStatus 실패");
                         }
                     }
        );

    }

    public void callUpdate() {

        Date curDate = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); // 시간 차이 용

        RetroBuilder retro = new RetroBuilder();
        SleepTimeUpdate stu = new SleepTimeUpdate(id, dateFormat.format(curDate));
        Call<SleepTimeUpdate> call3 = retro.service.provideSleepTimeUpdate(stu, "Bearer " + checkFirst);

        call3.enqueue(new Callback<SleepTimeUpdate>() {
                         @Override
                         public void onResponse(Call<SleepTimeUpdate> call, Response<SleepTimeUpdate> response) {
                             Log.v("알림", "callUpdate 돌입");
                             if (response.isSuccessful()) {
                                 Log.v("알림", "업데이트 성공");
                             }
                         }

                         @Override
                         public void onFailure(Call<SleepTimeUpdate> call, Throwable t) {
                             Log.v("알림", "callUpdate 실패" + t);
                         }
                     }
        );
    }

}