package com.example.sleepmood;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetroBuilder {

    Gson gson = new GsonBuilder()
            .setLenient() //building as lenient mode`enter code here`
            .create();

    Retrofit retrofit = new Retrofit.Builder().baseUrl("http://www.hanium-sleep.kro.kr/sleepapp/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build();

    RetroService service = retrofit.create(RetroService.class);

}
