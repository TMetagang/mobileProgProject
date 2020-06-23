package com.example.mobileprogproject.presentation.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mobileprogproject.Constants;
import com.example.mobileprogproject.Injection;
import com.example.mobileprogproject.R;
import com.example.mobileprogproject.presentation.controller.HomeController;
import com.example.mobileprogproject.presentation.controller.MainController;
import com.example.mobileprogproject.presentation.model.MPTv;
import com.squareup.picasso.Picasso;

public class HomeActivity extends AppCompatActivity {

    private Button button;
    private static int SPLASH_TIME_OUT = 4000;
    private HomeController controller;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_activity);

        controller = new HomeController(
                this,
                Injection.getGson(),
                getSharedPreferences(Constants.App_Name, Context.MODE_PRIVATE)

        );
        controller.onStart();

    }


}
