package com.example.e_gouvernance.service;

import com.example.e_gouvernance.entity.CommandeListResponse;
import com.example.e_gouvernance.entity.DocumentListResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface CommandeInterface {
    @FormUrlEncoded
    @POST("me/newCommande")
    Call<Void> sendOrder(@Header("Authorization") String authHeader,
            @Field("produit") String produit,
            @Field("datecommande") String datecommande
    );
    @GET("me/allCommande")
    Call<CommandeListResponse> getMyCommande(@Header("Authorization") String authHeader);
    @GET("all/rendreCommande/{id}")
    Call<Void> rendreCommande(@Header("Authorization")String authHeader ,
                              @Path("id") String id);
}
