package com.example.mobileprogproject.presentation.view;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mobileprogproject.Injection;
import com.example.mobileprogproject.R;
import com.example.mobileprogproject.presentation.model.MPTv;

public class DetailActivity extends AppCompatActivity {

    private TextView txtDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_activity);

        txtDetail = findViewById(R.id.detail_txt);

        Intent intent = getIntent();
        String tvJson = intent.getStringExtra("TVkey");

        MPTv tv = Injection.getGson().fromJson(tvJson, MPTv.class);

        showDetail(tv);

    }

    private void showDetail(MPTv tv) {
        txtDetail.setText(tv.getFullTitle());
    }
}
