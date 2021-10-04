package com.example.sleepmood;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import org.jetbrains.annotations.NotNull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.google.android.gms.common.internal.safeparcel.SafeParcelable.NULL;

public class Fragment_UserInfo_Drop extends Fragment {

    private Button userInfo_drop;

    private SharedPreferences pref;
    private String checkFirst;

    private SharedPreferences pref_id;
    private String user_id;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment__user_info__drop, container, false);
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        userInfo_drop = view.findViewById(R.id.userInfo_drop);

        pref = getActivity().getSharedPreferences("token", Activity.MODE_PRIVATE);
        checkFirst = pref.getString("token", "NULL");

        pref_id = getActivity().getSharedPreferences("id", Activity.MODE_PRIVATE);
        user_id = pref_id.getString("id", "NULL");


        userInfo_drop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userDelete();
            }
        });

    }

    public void userDelete() {

        RetroBuilder retro = new RetroBuilder();
        Call<Void> call = retro.service.deleteUser(user_id, "Bearer " + checkFirst);
        call.enqueue(new Callback<Void>() {
                         @Override
                         public void onResponse(Call<Void> call, Response<Void> response) {

                             if (!response.isSuccessful()) {
                                 Log.v("알림", "response : " + response.code());
                                 return;
                             }

                             Log.v("알림", "response : " + response.code());
                             SharedPreferences.Editor editor = pref.edit();
                             editor.putString("token", "NULL");
                             editor.commit();
                         }

                         @Override
                         public void onFailure(Call<Void> call, Throwable t) {
                             Log.v("알림", "실패");
                         }
                     }
        );
    }

}