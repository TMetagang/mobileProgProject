package com.example.mobileprogproject;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface TVApi {

    @GET("/API/MostPopularTVs/k_8rZ9Ie4z")
    Call<RestMPTvResponse> getMPTVResponse();
}
