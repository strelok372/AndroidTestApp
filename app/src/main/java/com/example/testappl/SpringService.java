package com.example.testappl;


import org.springframework.web.client.RestTemplate;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

interface SpringService {
    @GET("/message")
    public Call<List<SpringUser>> fetchAllUsers();
}
