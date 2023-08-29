package com.example.e_gouvernance.entity;

import com.google.gson.annotations.SerializedName;

public class ClientResponse {
    @SerializedName("me")
    private User user; // User est une classe pour représenter les données de l'utilisateur

    public User getUser() {
        return user;
    }
}
