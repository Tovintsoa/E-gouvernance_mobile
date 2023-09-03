package com.example.e_gouvernance.data;

import android.content.Context;

import com.example.e_gouvernance.conf.AppConfig;
import com.example.e_gouvernance.entity.CommandeListResponse;
import com.example.e_gouvernance.entity.DocumentListResponse;
import com.example.e_gouvernance.service.CommandeInterface;
import com.example.e_gouvernance.service.TokenService;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CommandeRepository {
    private final CommandeInterface commandeInterface;
    private final TokenService tokenService;
    public CommandeRepository(Context context) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(AppConfig.WEB_SERVICE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // Créez une instance de l'interface ClientInterface
        commandeInterface = retrofit.create(CommandeInterface.class);

        // Initialisez le service de gestion du token
        tokenService = new TokenService(context);
    }
    public Call<Void> sendCommande(String produit, String datecommande) {
        // Récupérez le token du service de gestion du token
        String token = "Bearer " + tokenService.getToken();
        // Effectuez l'appel au web service
        return commandeInterface.sendOrder(token,produit,datecommande);
    }
    public Call<CommandeListResponse> getMyCommande(){
        String token = "Bearer " + tokenService.getToken();
        return commandeInterface.getMyCommande(token);
    }
}
