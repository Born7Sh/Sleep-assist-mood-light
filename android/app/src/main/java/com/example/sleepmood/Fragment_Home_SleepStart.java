package com.example.sleepmood;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.jetbrains.annotations.NotNull;

import java.util.Timer;
import java.util.TimerTask;

public class Fragment_Home_SleepStart extends Fragment {

    private TextView tv_roll, tv_pitch;
    private TextView tv_grade;

    private SensorManager mSensorManager = null;
    private UserSensorListner userSensorListner;
    private Sensor mGyroscopeSensor = null;
    private Sensor mAccelerometer = null;

    private int waterC = 0;
    private int awakeC = 0;
    private int toiletC = 0;

    private int grade;

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

        Timer timer = new Timer();
        TimerTask TT = new TimerTask() {
            @Override
            public void run() {
                // 반복실행할 구문
                if(roll>5){
                    grade++;
                }
            }
        };

        timer.schedule(TT, 0, 1000); //Timer 실행
//        timer.cancel();//타이머 종료

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

        TextView waterCount = (TextView)view.findViewById(R.id.waterCount);
        TextView awakeCount = (TextView)view.findViewById(R.id.awakeCount);
        TextView toiletCount = (TextView)view.findViewById(R.id.toiletCount);

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
                grade = waterC + awakeC + toiletC + grade;
                tv_grade.setText("grade : " + grade);
            }
        });


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

                /** GYROSCOPE */
                case Sensor.TYPE_GYROSCOPE:

                    /*센서 값을 mGyroValues에 저장*/
                    mGyroValues = event.values;

                    if (!gyroRunning)
                        gyroRunning = true;

                    break;

                /** ACCELEROMETER */
                case Sensor.TYPE_ACCELEROMETER:

                    /*센서 값을 mAccValues에 저장*/
                    mAccValues = event.values;

                    if (!accRunning)
                        accRunning = true;

                    break;

            }

            /**두 센서 새로운 값을 받으면 상보필터 적용*/
            if (gyroRunning && accRunning) {

                if(roll>5){
                    grade++;
                }

                if((int)Math.round(roll) > 5){
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


}