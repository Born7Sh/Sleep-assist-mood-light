package com.example.sleepmood;


import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetroBuilderSleep {
    Retrofit retrofit = new Retrofit.Builder().baseUrl("http://localhost:8080/sleepapp/status/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    RetroServiceSleep service = retrofit.create(RetroServiceSleep.class);

}
