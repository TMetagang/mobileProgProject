package com.example.mobileprogproject.presentation.view;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mobileprogproject.Injection;
import com.example.mobileprogproject.R;
import com.example.mobileprogproject.presentation.model.MPTv;
import com.squareup.picasso.Picasso;

public class DetailActivity extends AppCompatActivity {

    private TextView txtDetail;
    public ImageView imageView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_activity);

        txtDetail = findViewById(R.id.detail_txt);
        imageView = findViewById(R.id.myImage);

        Intent intent = getIntent();
        String tvJson = intent.getStringExtra("TVkey");

        MPTv tv = Injection.getGson().fromJson(tvJson, MPTv.class);

        showDetail(tv);

    }

    private void showDetail(MPTv tv) {
        txtDetail.setText(" Title: " + tv.getFullTitle() +"\n" + " Crew: " + tv.getCrew()+ "\n" + " Rank: " + tv.getRank() +"\n" + " Rank up down: " + tv.getRankUpDown() + "\n" + " Im Db rating: " + tv.getImDbRating() + "\n" + " Im Db rating count: " + tv.getImDbRatingCount() + "\n" );
        Picasso.get()
                .load(tv.getImage())
                .into(imageView);

    }
}
