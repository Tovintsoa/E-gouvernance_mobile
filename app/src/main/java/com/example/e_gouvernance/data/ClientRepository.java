package com.example.e_gouvernance.data;

import static android.content.Context.MODE_PRIVATE;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.e_gouvernance.MainActivity;
import com.example.e_gouvernance.R;
import com.example.e_gouvernance.StartActivity;
import com.example.e_gouvernance.conf.AppConfig;
import com.example.e_gouvernance.entity.ClientResponse;
import com.example.e_gouvernance.entity.TokenResponse;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ClientRepository extends AsyncTask<String, Void, String> {
    private AppCompatActivity activity;
    public ClientRepository (AppCompatActivity mActivity){
        activity = mActivity;
    }

    @Override
    protected String doInBackground(String... strings) {
        try {
            /*TokenResponse tokenResponse = new TokenResponse();
            String token = tokenResponse.getToken();*/
            SharedPreferences preferences = activity.getSharedPreferences("Session",MODE_PRIVATE);
            String token  = preferences.getString("token",null);
            URL url = new URL(AppConfig.WEB_SERVICE_URL+"me");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setDoOutput(true);
            conn.addRequestProperty("Content-Type", "application/json");
            conn.addRequestProperty("Authorization","Bearer " + token);

            OutputStream os = conn.getOutputStream();
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
            writer.flush();
            writer.close();
            os.close();

            int responseCode = conn.getResponseCode();
            System.out.println(AppConfig.WEB_SERVICE_URL+"me");
            System.out.println(responseCode);
            if (responseCode == HttpURLConnection.HTTP_OK) {
                // Lire la réponse du serveur
                InputStream inputStream = conn.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                StringBuilder response = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                reader.close();
                return response.toString();
            } else {
                return "Échec de la connexion";
            }
        } catch (IOException e) {
            e.printStackTrace();
            return "Erreur réseau";
        }
    }
    @Override
    protected void onPostExecute(String result) {
           System.out.println(result);
    }

}
