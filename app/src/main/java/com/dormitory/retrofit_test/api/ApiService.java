package com.dormitory.retrofit_test.api;

import com.dormitory.retrofit_test.model.PostList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {

    @GET("posts/1")
    Call<PostList> getMyJSON();

}
