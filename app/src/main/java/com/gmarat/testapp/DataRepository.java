package com.gmarat.testapp;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.gmarat.testapp.api.ServiceApi;
import com.gmarat.testapp.api.ServiceGenerator;
import com.gmarat.testapp.model.Data;
import com.gmarat.testapp.model.DataItem;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DataRepository {

    public LiveData<List<DataItem>> loadData(String query) {
        MutableLiveData<List<DataItem>> liveData = new MutableLiveData<>();
        ServiceGenerator.createService(ServiceApi.class).getData(query).enqueue(new Callback<Data>() {
            @Override
            public void onResponse(@NotNull Call<Data> call, @NotNull Response<Data> response) {
                if (response.isSuccessful() && response.body() != null) {
                    liveData.setValue(response.body().getDataItems());
                }
            }

            @Override
            public void onFailure(@NotNull Call<Data> call, @NotNull Throwable t) {

            }
        });
        return liveData;
    }
}
