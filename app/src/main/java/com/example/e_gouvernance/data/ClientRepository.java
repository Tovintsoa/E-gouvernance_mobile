package com.example.e_gouvernance.data;


import android.content.Context;

import com.example.e_gouvernance.conf.AppConfig;
import com.example.e_gouvernance.entity.ClientResponse;
import com.example.e_gouvernance.service.ClientInterface;
import com.example.e_gouvernance.service.TokenService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ClientRepository {
    private final ClientInterface clientInterface;
    private final TokenService tokenService;

    public ClientRepository(Context context) {
        // Initialisez Retrofit avec l'URL de base
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(AppConfig.WEB_SERVICE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // Créez une instance de l'interface ClientInterface
        clientInterface = retrofit.create(ClientInterface.class);

        // Initialisez le service de gestion du token
        tokenService = new TokenService(context);
    }

    public Call<ClientResponse> getUserProfile() {
        // Récupérez le token du service de gestion du token
        String token = "Bearer " + tokenService.getToken();

        // Effectuez l'appel au web service
        return clientInterface.getUserProfil(token);
    }
}

