package com.example.e_gouvernance.data;

import static android.content.Context.MODE_PRIVATE;

import android.content.SharedPreferences;
import android.os.AsyncTask;
import androidx.appcompat.app.AppCompatActivity;
import com.example.e_gouvernance.conf.AppConfig;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;


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
            conn.setRequestProperty("Authorization","Bearer " + token);
            conn.setRequestMethod("GET");
            conn.connect();


            OutputStream os = conn.getOutputStream();
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
            writer.flush();
            writer.close();
            os.close();

            int responseCode = conn.getResponseCode();
            System.out.println(AppConfig.WEB_SERVICE_URL+"me/");
            System.out.println(conn.getRequestMethod());
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
