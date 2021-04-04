package com.example.retrofitpractice;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiServices
{
    @GET("posts")
    Call<List<Model>> getModels();



}
