package com.example.sleepmood;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import org.jetbrains.annotations.NotNull;

public class Fragment_Record_Diary_Add extends Fragment {

    TextView diary_description;

    Button diary_cancel;
    Button diary_register;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment__record__diary__add, container, false);
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        diary_description = (TextView) view.findViewById(R.id.diary_description);
        diary_cancel = (Button) view.findViewById(R.id.diaryCancel);
        diary_register = (Button) view.findViewById(R.id.diaryRegister);

    }
}