package com.example.sleepmood;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import org.jetbrains.annotations.NotNull;

public class Fragment_Light extends Fragment {
    private int color = 1;
    ImageView mood_State;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_light, container, false);
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mood_State = (ImageView) view.findViewById(R.id.mood_State);


        mood_State.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                color++;
                if(color==6){
                    color=1;
                }

                switch (color) {
                    case 1:
                        mood_State.setImageResource(R.drawable.mood_red);
                        break;
                    case 2:
                        mood_State.setImageResource(R.drawable.mood_yellow);
                        break;
                    case 3:
                        mood_State.setImageResource(R.drawable.mood_green);
                        break;
                    case 4:
                        mood_State.setImageResource(R.drawable.mood_blue);
                        break;
                    case 5:
                        mood_State.setImageResource(R.drawable.mood_purple);
                        break;
                }
            }
        });

        setMoodState();

    }

    void setMoodState() {
        switch (color) {
            case 1:
                mood_State.setImageResource(R.drawable.mood_red);
                break;
            case 2:
                mood_State.setImageResource(R.drawable.mood_yellow);
                break;
            case 3:
                mood_State.setImageResource(R.drawable.mood_green);
                break;
            case 4:
                mood_State.setImageResource(R.drawable.mood_blue);
                break;
            case 5:
                mood_State.setImageResource(R.drawable.mood_purple);
                break;
        }
    }

}