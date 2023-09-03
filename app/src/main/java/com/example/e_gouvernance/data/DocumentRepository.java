package com.example.e_gouvernance.data;

import android.content.Context;

import com.example.e_gouvernance.conf.AppConfig;
import com.example.e_gouvernance.entity.ClientResponse;
import com.example.e_gouvernance.entity.DocumentListResponse;
import com.example.e_gouvernance.entity.DocumentResponse;
import com.example.e_gouvernance.entity.DocumentUniqueResponse;
import com.example.e_gouvernance.service.ClientInterface;
import com.example.e_gouvernance.service.DocumentInterface;
import com.example.e_gouvernance.service.TokenService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DocumentRepository {
    private final DocumentInterface documentInterface;
    private final TokenService tokenService;
    public DocumentRepository(Context context) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(AppConfig.WEB_SERVICE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // Créez une instance de l'interface ClientInterface
        documentInterface = retrofit.create(DocumentInterface.class);

        // Initialisez le service de gestion du token
        tokenService = new TokenService(context);
    }
    public Call<DocumentListResponse> getDocuments() {
        // Récupérez le token du service de gestion du token
        String token = "Bearer " + tokenService.getToken();

        // Effectuez l'appel au web service
        return documentInterface.getDocuments(token);
    }
    public Call<DocumentUniqueResponse> getOneDocument(String produit){
        String token = "Bearer " + tokenService.getToken();
        return documentInterface.getDocument(token,produit);
    }

}
