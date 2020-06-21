package com.example.mobileprogproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

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

    private static final String BASE_URL = "https://imdb-api.com/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        makeApiCall();


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

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

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


    private void showError(){

        Toast.makeText(getApplicationContext(), "API Error", Toast.LENGTH_SHORT).show();

    }
}
