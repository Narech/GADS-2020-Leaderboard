package com.sounach.gads2020leaderboard.data.networking;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class ApiClient {

    private static String BASE_URL = "https://gadsapi.herokuapp.com/api/";
    private static String BASE_URL_1 = "https://docs.google.com/forms/d/e/";


    public static OkHttpClient okHttpClient = new OkHttpClient.Builder()
            .connectTimeout(2, TimeUnit.MINUTES)
            .readTimeout(150000, TimeUnit.MILLISECONDS)
            .writeTimeout(1500000, TimeUnit.MILLISECONDS)
            .build();

    public static Gson gson = new GsonBuilder()
            .setLenient()
            .create();


    private static Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build();

    public static <S> S createService(Class<S> serviceClass) {
        return retrofit.create(serviceClass);
    }

    private static Retrofit retrofit1 = new Retrofit.Builder()
            .baseUrl(BASE_URL_1)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build();

    public static <S> S createService1(Class<S> serviceClass) {
        return retrofit1.create(serviceClass);
    }
}
