package com.example.mobileprogproject.data;

import android.content.SharedPreferences;

import com.example.mobileprogproject.Constants;
import com.example.mobileprogproject.presentation.model.MPTv;
import com.example.mobileprogproject.presentation.model.RestMPTvResponse;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TVRepository {

    private TVApi tvApi;
    private SharedPreferences sharedPreferences;
    private final Gson gson;

    public TVRepository(TVApi tvApi, SharedPreferences sharedPreferences, Gson gson){
        this.tvApi = tvApi;
        this.sharedPreferences = sharedPreferences;
        this.gson = gson;
    }

    public void getMPTVResponse(final TVCallback callback){
        List<MPTv> list = getDataFromCache();
        if(list != null){
            callback.onSuccess(list);
        }

        tvApi.getMPTVResponse().enqueue(new Callback<RestMPTvResponse>() {
            @Override
            public void onResponse(Call<RestMPTvResponse> call, Response<RestMPTvResponse> response) {
                if (response.isSuccessful() && response.body() != null){
                    callback.onSuccess(response.body().items);
                }else{
                    callback.onFailed();
                }
            }

            @Override
            public void onFailure(Call<RestMPTvResponse> call, Throwable t) {

                callback.onFailed();
            }
        });
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

    private void saveList(List<MPTv> listTv) {
        String jsonString = gson.toJson(listTv);
        sharedPreferences
                .edit()
                .putString(Constants.KEY_TV_LIST, jsonString)
                .apply();
    }


}
