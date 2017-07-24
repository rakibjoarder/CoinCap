package com.aust.rakib.coincap;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

/**
 * Created by Personal on 7/22/2017.
 */

public interface CoinService {
    @GET("v1/ticker/?limit=4")
    Call<ArrayList<CoinResponse>> coinListCall();
}
