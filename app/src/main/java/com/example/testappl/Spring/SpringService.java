package com.example.testappl.Spring;


import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

interface SpringService {
    @GET("/recs")
    Call<List<SpringUser>> fetchAllUsers(); //получение всех записей

    @GET("/recs")
    Call<List<SpringUser>> getRecordsByUser(@Query("id") Integer UserId); //записи по id пользователя

    @POST("/recs")
    Call<SpringUser> newRecord(@Body SpringUser user); //добавление новой записи

    @PATCH("/recs/{id}")
    Call<SpringUser> updRecord(@Path("id") int id, @Body SpringUser user);

    @DELETE("/recs/{id}")
    Call<Void> delRecord(@Path("id") int id);
}
