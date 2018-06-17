package com.valle00018316.gamenews.GameNApi;



import com.valle00018316.gamenews.Dba.Entidad.Noticia;
import com.valle00018316.gamenews.Models.NoticiaM;
import com.valle00018316.gamenews.Models.PlayerM;

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
    @GET("/players")
    Call<List<PlayerM>> getPlayers(@Header("Authorization") String authorization);
    @GET("/news/type/list")
    Call<List<String>> list(@Header("Authorization") String autori);

    @GET("/news")
    Call<List<NoticiaM>>noticias(@Header("Authorization") String autori);
}