package com.gmarat.testapp.api;

import com.gmarat.testapp.model.Data;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ServiceApi {

    @GET("xim/api.php")
    Call<Data> getData(@Query("query") String query);
}
