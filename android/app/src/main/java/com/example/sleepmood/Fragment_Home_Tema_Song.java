package com.example.sleepmood;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import org.jetbrains.annotations.NotNull;

public class Fragment_Home_Tema_Song extends Fragment {

    TextView songWood;
    TextView songRain;
    TextView songNewAge;

    TextView currentSong;

    MainActivity activity;
    private MediaPlayer mediaPlayer;

    private int a = R.raw.wood;
    private int b = R.raw.rain;
    private int c = R.raw.newage;

    SharedPreferences pref;
    OnBackPressedCallback callback;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activity = (MainActivity) getActivity();
    }

    public void onDetach() {
        super.onDetach();
        activity = null;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment__home__tema__song, container, false);
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        songWood = (TextView) view.findViewById(R.id.songWood);
        songRain = (TextView) view.findViewById(R.id.songRain);
        songNewAge = (TextView) view.findViewById(R.id.songNewAge);
        currentSong = (TextView) view.findViewById(R.id.currentSong);

        pref = getActivity().getSharedPreferences("song", Activity.MODE_PRIVATE);

        Switch song_sw = (Switch) view.findViewById(R.id.song_Sw);
        EditText song_Vol = (EditText) view.findViewById(R.id.song_Vol);

        if (pref.getInt("song", R.raw.wood) == R.raw.wood) {
            currentSong.setText("장작 타는 소리");
        } else if (pref.getInt("song", R.raw.wood) == R.raw.rain) {
            currentSong.setText("비 내리는 소리");
        } else if (pref.getInt("song", R.raw.wood) == R.raw.newage) {
            currentSong.setText("뉴에이지");
        }

        songWood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mediaPlayer != null) {
                    if (mediaPlayer.isPlaying()) {
                        mediaPlayer.stop();
                        mediaPlayer.reset();

                    }
                }
                mediaPlayer = MediaPlayer.create(getActivity(), R.raw.wood);
                mediaPlayer.start();
                pref = getActivity().getSharedPreferences("song", Activity.MODE_PRIVATE);
                SharedPreferences.Editor editor = pref.edit();
                editor.putInt("song", R.raw.wood);
                editor.commit();

                currentSong.setText("장작 타는 소리");


                Log.v("알림", "user_song 값 : " + pref.getInt("song", R.raw.wood));

            }
        });

        songRain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mediaPlayer != null) {
                    if (mediaPlayer.isPlaying()) {
                        mediaPlayer.stop();
                        mediaPlayer.reset();

                    }
                }
                mediaPlayer = MediaPlayer.create(getActivity(), R.raw.rain);
                mediaPlayer.start();

                pref = getActivity().getSharedPreferences("song", Activity.MODE_PRIVATE);
                SharedPreferences.Editor editor = pref.edit();
                editor.putInt("song", R.raw.rain);
                editor.commit();

                currentSong.setText("비 내리는 소리");

                Log.v("알림", "user_song 값 : " + pref.getInt("song", R.raw.wood));
            }
        });

        songNewAge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (mediaPlayer != null) {
                    if (mediaPlayer.isPlaying()) {
                        mediaPlayer.stop();
                        mediaPlayer.reset();

                    }
                }
                mediaPlayer = MediaPlayer.create(getActivity(), R.raw.newage);
                mediaPlayer.start();

                pref = getActivity().getSharedPreferences("song", Activity.MODE_PRIVATE);
                SharedPreferences.Editor editor = pref.edit();
                editor.putInt("song", R.raw.newage);
                editor.commit();

                currentSong.setText("뉴에이지");

                Log.v("알림", "user_song 값 : " + pref.getInt("song", R.raw.wood));


            }
        });


    }

    @Override
    public void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        OnBackPressedCallback callback = new OnBackPressedCallback(true /* enabled by default */) {
            @Override
            public void handleOnBackPressed() {
                // Handle the back button event
                if (mediaPlayer != null) {
                    if (mediaPlayer.isPlaying()) {
                        mediaPlayer.stop();
                        mediaPlayer.reset();

                    }
                }
                activity.onFragmentChange("home");
            }
        };
        requireActivity().getOnBackPressedDispatcher().addCallback(this, callback);

    }
}
