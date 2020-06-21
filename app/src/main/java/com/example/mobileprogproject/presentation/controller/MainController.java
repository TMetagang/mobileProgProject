package com.example.mobileprogproject.presentation.controller;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

import com.example.mobileprogproject.Constants;
import com.example.mobileprogproject.Injection;
import com.example.mobileprogproject.data.TVApi;
import com.example.mobileprogproject.presentation.model.MPTv;
import com.example.mobileprogproject.presentation.model.RestMPTvResponse;
import com.example.mobileprogproject.presentation.view.MainActivity;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.Callback;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainController {

    private SharedPreferences sharedPreferences;
    private Gson gson;
    private MainActivity view;

    public MainController(MainActivity mainActivity, Gson gson, SharedPreferences sharedPreferences){
        this.gson = gson;
        this.sharedPreferences = sharedPreferences;
        this.view = mainActivity;

    }


    public void onStart(){
        List<MPTv> listTV = getDataFromCache();
        if(listTV != null){
            view.showList(listTV);
        }else{
            makeApiCall();
        }
    }

    private void makeApiCall()
    {

        Call<RestMPTvResponse> call = Injection.getTVApi().getMPTVResponse();
        call.enqueue(new Callback<RestMPTvResponse>() {
            @Override
            public void onResponse(Call<RestMPTvResponse> call, Response<RestMPTvResponse> response) {
                if(response.isSuccessful() && response.body() != null){
                    List<MPTv> listTv = response.body().items;
                    saveList(listTv);
                    view.showList(listTv);
                }else{
                    view.showError();
                }
            }
            @Override
            public void onFailure(Call<RestMPTvResponse> call, Throwable t) {
                view.showError();
            }
        });

    }

    private void saveList(List<MPTv> listTv) {
        String jsonString = gson.toJson(listTv);
        sharedPreferences
                .edit()
                .putString(Constants.KEY_TV_LIST, jsonString)
                .apply();
    }

    private List<MPTv> getDataFromCache() {

        String jsonTV = sharedPreferences.getString(Constants.KEY_TV_LIST, null);
        if(jsonTV == null){
            return null;
        }else{

            Type listType = new TypeToken<List<MPTv>>(){}.getType();
            return gson.fromJson(jsonTV, listType);
        }

    }



    public void onItemClick(MPTv tv){
        view.navigateToDetails(tv);
    }

    public void onButtonAClick(){}

    public void onButtonBClick(){}
}
