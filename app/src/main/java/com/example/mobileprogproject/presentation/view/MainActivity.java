package com.example.mobileprogproject.presentation.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import com.example.mobileprogproject.Constants;

import com.example.mobileprogproject.presentation.controller.MainController;
import com.example.mobileprogproject.presentation.view.ListAdapter;
import com.example.mobileprogproject.presentation.model.MPTv;
import com.example.mobileprogproject.R;

import com.example.mobileprogproject.presentation.model.RestMPTvResponse;
import com.example.mobileprogproject.data.TVApi;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
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

    private MainController controller;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        controller = new MainController(
                this,
                 new GsonBuilder()
                        .setLenient()
                        .create(),
                 getSharedPreferences(Constants.App_Name, Context.MODE_PRIVATE)

        );
        controller.onStart();






    }

    public void showList(List<MPTv> TvList){

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        mAdapter = new ListAdapter(TvList);
        recyclerView.setAdapter(mAdapter);

    }

    public void showError(){

        Toast.makeText(getApplicationContext(), "API Error", Toast.LENGTH_SHORT).show();

    }
}