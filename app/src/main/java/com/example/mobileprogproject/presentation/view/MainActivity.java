package com.example.mobileprogproject.presentation.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import com.example.mobileprogproject.Constants;

import com.example.mobileprogproject.Injection;
import com.example.mobileprogproject.presentation.controller.MainController;
import com.example.mobileprogproject.presentation.model.MPTv;
import com.example.mobileprogproject.R;

import java.util.List;

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
                Injection.getGson(),
                 getSharedPreferences(Constants.App_Name, Context.MODE_PRIVATE)

        );
        controller.onStart();


    }

    public void showList(List<MPTv> TvList){

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        mAdapter = new ListAdapter(TvList, new ListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(MPTv item) {
                controller.onItemClick(item);
            }
        });
        recyclerView.setAdapter(mAdapter);

    }

    public void showError(){

        Toast.makeText(getApplicationContext(), "API Error", Toast.LENGTH_SHORT).show();

    }

    public void navigateToDetails(MPTv tv) {
        Intent myIntent = new Intent(MainActivity.this, DetailActivity.class);
        myIntent.putExtra("TVkey", Injection.getGson().toJson(tv));
        MainActivity.this.startActivity(myIntent);

    }
}
