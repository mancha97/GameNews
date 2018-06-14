package com.valle00018316.gamenews.GameNApi;



import java.lang.reflect.Array;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface Api {

    static final String END_POINT = "http://gamenewsuca.herokuapp.com";

    @FormUrlEncoded
    @POST("/login")
    Call<String> login(
            @Field("user") String username,
            @Field("password") String password
    );

    @GET("/news/type/list")
    Call<List<String>> list(@Header("Authorization") String autori);
}