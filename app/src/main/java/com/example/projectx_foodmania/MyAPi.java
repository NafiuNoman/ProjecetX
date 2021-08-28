package com.example.projectx_foodmania;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface MyAPi {


    @GET("335e245a-ed48-49f5-9533-575bfd53e65b")
    Call<List<FoodDataModel>> getData();

}
