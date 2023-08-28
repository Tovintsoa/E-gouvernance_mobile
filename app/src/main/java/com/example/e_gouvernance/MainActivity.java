package com.example.e_gouvernance;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.e_gouvernance.conf.AppConfig;
import com.example.e_gouvernance.entity.TokenResponse;
import com.example.e_gouvernance.ui.utilities.Loading;
import com.google.gson.Gson;
import com.google.gson.JsonParseException;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    EditText username;
    EditText password;
    Button loginButton;
    Loading loadingDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        loginButton = findViewById(R.id.loginButton);
        loadingDialog = new Loading(this);


        SharedPreferences sharedPreferences = getSharedPreferences("Session", MODE_PRIVATE);
        if (sharedPreferences.contains("token")) {
            Intent intent = new Intent(MainActivity.this, StartActivity.class);
            startActivity(intent);
        }
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String enteredUsername = username.getText().toString();
                String enteredPassword = password.getText().toString();
                new HttpPostTask().execute(enteredUsername, enteredPassword);
            }
        });
    }
    private class HttpPostTask extends AsyncTask<String, Void, String> {
        protected void onPreExecute() {
            // Affichez le ProgressBar avant le chargement
            loadingDialog.showLoadingModal();
        }
        @Override
        protected String doInBackground(String... params) {
            String enteredUsername = params[0];
            String enteredPassword = params[1];

            try {
                URL url = new URL(AppConfig.WEB_SERVICE_URL+"login");
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("POST");
                conn.setDoOutput(true);
                conn.setRequestProperty("Content-Type", "application/json");
                System.out.println(enteredUsername); System.out.println(enteredPassword);
                String data = "{\"username\":\"" + enteredUsername + "\",\"password\":\"" + enteredPassword + "\"}";
                OutputStream os = conn.getOutputStream();
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
                writer.write(data);
                writer.flush();
                writer.close();
                os.close();

                int responseCode = conn.getResponseCode();
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
            // result contient la réponse entière du serveur
            //Toast.makeText(MainActivity.this, result, Toast.LENGTH_SHORT).show();
            if (isTokenResponse(result)) {

                Gson gson = new Gson();
                TokenResponse tokenResponse = gson.fromJson(result, TokenResponse.class);
                String token = tokenResponse.getToken();

                SharedPreferences sharedPreferences = getSharedPreferences("Session", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("token", token);
                editor.apply();
                Intent intent = new Intent(MainActivity.this, StartActivity.class);
                startActivity(intent);
            }
            else{
                TextView errorMessageTextView = findViewById(R.id.errorMessage);
                errorMessageTextView.setText("Login ou mot de passe incorrect");
            }
            loadingDialog.hideLoadingModal();

        }
        public boolean isTokenResponse(String jsonString) {
            try {
                Gson gson = new Gson();
                TokenResponse tokenResponse = gson.fromJson(jsonString, TokenResponse.class);

                // Check if the parsed object contains a token
                return tokenResponse != null && tokenResponse.getToken() != null;
            } catch (JsonParseException e) {
                // JSON parsing failed, not a valid JSON object
                return false;
            }
        }

    }
}
