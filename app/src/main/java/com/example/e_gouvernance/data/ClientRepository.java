package com.example.e_gouvernance.data;

import com.example.e_gouvernance.conf.AppConfig;
import com.example.e_gouvernance.entity.ClientResponse;
import com.example.e_gouvernance.entity.TokenResponse;
import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ClientRepository {
    public void getDetailClient(){
        OkHttpClient client = new OkHttpClient();
        TokenResponse tokenResponse = new TokenResponse();
        String token = tokenResponse.getToken();
        Request request = new Request.Builder()
                .url(AppConfig.WEB_SERVICE_URL+"me")
                .addHeader("Authorization", "Bearer " + token)
                .build();

        try {
            // Exécutez la requête GET
            Response response = client.newCall(request).execute();
            System.out.println(response);

            // Vérifiez si la réponse est réussie (code 200)
           /* if (response.isSuccessful()) {
                String responseBody = response.body().string();
                Gson gson = new Gson();
                return gson.fromJson(responseBody, ClientResponse.class);
            } else {
                // La requête a échoué, vous pouvez gérer les erreurs ici
            }*/
        } catch (IOException e) {
            e.printStackTrace();
            // Une exception s'est produite, gérer les erreurs ici
        }

    }
}
