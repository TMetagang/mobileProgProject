package com.example.mobileprogproject.presentation.controller;

import android.content.Intent;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.mobileprogproject.R;
import com.example.mobileprogproject.presentation.model.MPTv;
import com.example.mobileprogproject.presentation.view.HomeActivity;
import com.example.mobileprogproject.presentation.view.MainActivity;
import com.google.gson.Gson;

import java.util.List;

public class HomeController {
    private SharedPreferences sharedPreferences;
    private Gson gson;
    private HomeActivity view;

    public HomeController(HomeActivity homeActivity, Gson gson, SharedPreferences sharedPreferences){
        this.gson = gson;
        this.sharedPreferences = sharedPreferences;
        this.view = homeActivity;

    }

    public void onStart(){

        ButtonShowList();

    }



    private void ButtonShowList(){

        Button buttonShowList = view.findViewById(R.id.button);
        buttonShowList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(view, MainActivity.class);
                view.startActivity(intent);
            }
        });

    }

}
