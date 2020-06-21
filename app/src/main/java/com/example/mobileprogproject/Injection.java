package com.example.mobileprogproject;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.mobileprogproject.data.TVApi;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Injection {

    private static Gson gsonInstance;
    private static TVApi tvApiInstance;
    private static SharedPreferences sharedPreferencesInstance;

    public static Gson getGson(){
        if(gsonInstance == null) {
            gsonInstance = new GsonBuilder()
                    .setLenient()
                    .create();
        }
        return gsonInstance;
    }


    public static TVApi getTVApi(){
        if(tvApiInstance == null){
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(Constants.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(getGson()))
                    .build();
            tvApiInstance = retrofit.create(TVApi.class);


        }
        return tvApiInstance;
    }

    public static SharedPreferences getSharedPreferences(Context context){
        if(sharedPreferencesInstance == null) {
            sharedPreferencesInstance = context.getSharedPreferences(Constants.App_Name, Context.MODE_PRIVATE);

        }
        return sharedPreferencesInstance;
    }

}
