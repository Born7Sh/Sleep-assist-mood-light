package com.example.sleepmood;


import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetroBuilder {
    Retrofit retrofit = new Retrofit.Builder().baseUrl("http://www.hanium-sleep.kro.kr/sleepapp/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    RetroService service = retrofit.create(RetroService.class);

}
