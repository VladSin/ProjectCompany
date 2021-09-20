package com.example.projectCompany.controller.config;

import com.example.projectCompany.controller.DepartmentUtilApi;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.util.concurrent.TimeUnit;

public class DepartmentApiConfig {

    // private static final String SERVER_URL = "https://project-company.herokuapp.com";
    private static final String SERVER_URL = "http://localhost:8080";
    private static DepartmentUtilApi instance;

    public DepartmentApiConfig() {
    }

    public static synchronized DepartmentUtilApi getApi() {
        if (instance == null) {

            final OkHttpClient okHttpClient = new OkHttpClient.Builder()
                    .readTimeout(60, TimeUnit.SECONDS)
                    .connectTimeout(60, TimeUnit.SECONDS)
                    .build();

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(SERVER_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(okHttpClient)
                    .build();
            instance = retrofit.create(DepartmentUtilApi.class);
        }

        return instance;
    }
}
