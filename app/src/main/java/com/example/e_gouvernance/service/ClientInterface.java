package com.example.e_gouvernance.service;

import com.example.e_gouvernance.entity.ClientResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface ClientInterface {
    @GET("me/")
    Call<ClientResponse> getUserProfil(@Header("Authorization") String authHeader);
}
