package com.example.mobileprogproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ListAdapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private SharedPreferences sharedPreferences;
    private Gson gson;

    private static final String BASE_URL = "https://imdb-api.com/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedPreferences = getSharedPreferences(Constants.App_Name, Context.MODE_PRIVATE);


        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        List<MPTv> listTV = getDataFromCache();
        if(listTV != null){
            showList(listTV);
        }else{
            makeApiCall();
        }




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


    private void showList(List<MPTv> TvList){

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        mAdapter = new ListAdapter(TvList);
        recyclerView.setAdapter(mAdapter);

    }

    private void makeApiCall()

    {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        TVApi TvApi = retrofit.create(TVApi.class);

        Call<RestMPTvResponse> call = TvApi.getMPTVResponse();
       call.enqueue(new Callback<RestMPTvResponse>() {
           @Override
           public void onResponse(Call<RestMPTvResponse> call, Response<RestMPTvResponse> response) {
               if(response.isSuccessful() && response.body() != null){

                   List<MPTv> listTv = response.body().results;
                   saveList(listTv);
                   Toast.makeText(getApplicationContext(), "API Success", Toast.LENGTH_SHORT).show();
                    showList(listTv);
               }else{
                   //showError();
                   Toast.makeText(getApplicationContext(), response.message(), Toast.LENGTH_SHORT).show();

               }
           }

           @Override
           public void onFailure(Call<RestMPTvResponse> call, Throwable t) {
                showError();
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


    private void showError(){

        Toast.makeText(getApplicationContext(), "API Error", Toast.LENGTH_SHORT).show();

    }
}
