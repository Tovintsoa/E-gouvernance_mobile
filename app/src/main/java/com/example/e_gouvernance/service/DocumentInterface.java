package com.example.e_gouvernance.service;

import com.example.e_gouvernance.entity.ClientResponse;
import com.example.e_gouvernance.entity.DocumentListResponse;
import com.example.e_gouvernance.entity.DocumentResponse;
import com.example.e_gouvernance.entity.DocumentUniqueResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;

public interface DocumentInterface {
    @GET("all/allProduit")
    Call<DocumentListResponse> getDocuments(@Header("Authorization") String authHeader);
    @GET("all/produit/{id}")
    Call<DocumentUniqueResponse> getDocument(@Header("Authorization") String authHeader, @Path("id") String produit);



}
