package com.valle00018316.gamenews.GameNApi;



import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface Api {

    static final String END_POINT = "http://gamenewsuca.herokuapp.com";
    @FormUrlEncoded
    @POST("/login")
    Call<String> login(
            @Field("user") String username,
            @Field("password") String password
    );
}
