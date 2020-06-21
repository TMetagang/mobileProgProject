package com.example.mobileprogproject.data;

//import com.example.mobileprogproject.presentation.model.RestMPTvResponse;

import com.example.mobileprogproject.presentation.model.RestMPTvResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface TVApi {

    @GET("/API/MostPopularTVs/k_8rZ9Ie4z")
    Call<RestMPTvResponse> getMPTVResponse();
}
