package com.example.sleepmood;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetroBuilder {

    Gson gson = new GsonBuilder()
            .setLenient() //building as lenient mode`enter code here`
            .create();

    OkHttpClient client = new OkHttpClient.Builder()
            .connectTimeout(100, TimeUnit.SECONDS)
            .readTimeout(100, TimeUnit.SECONDS).build();

    Retrofit retrofit = new Retrofit.Builder().baseUrl("http://www.hanium-sleep.kro.kr/sleepapp/").client(client)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build();

    RetroService service = retrofit.create(RetroService.class);

}
