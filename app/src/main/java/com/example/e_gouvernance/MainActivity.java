package com.example.e_gouvernance;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    EditText username;
    EditText password;
    Button loginButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username = (EditText) findViewById(R.id.loginText);
        password = (EditText) findViewById(R.id.password);
        loginButton = findViewById(R.id.loginButton);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String enteredUsername = username.getText().toString();
                String enteredPassword = password.getText().toString();
                new HttpPostTask().execute(enteredUsername, enteredPassword);
                /*Intent intent = new Intent(MainActivity.this, StartActivity.class);
                startActivity(intent);*/
            }
        });
    }
    private class HttpPostTask extends AsyncTask<String, Void, Integer> {
        @Override
        protected Integer doInBackground(String... params) {
            String enteredUsername = params[0];
            String enteredPassword = params[1];

            try {
                URL url = new URL("https://tptegov.onrender.com/login");
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("POST");
                conn.setDoOutput(true);
                String data = "username=" + enteredUsername + "&password=" + enteredPassword;

                OutputStream os = conn.getOutputStream();
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
                writer.write(data);
                writer.flush();
                writer.close();
                os.close();

                return conn.getResponseCode();
            } catch (IOException e) {
                e.printStackTrace();
                return -1; // Une erreur s'est produite
            }
        }

        @Override
        protected void onPostExecute(Integer responseCode) {
            if (responseCode == HttpURLConnection.HTTP_OK) {
                // La requête a réussi, vous pouvez lire la réponse ici
                Toast.makeText(MainActivity.this, "Connexion réussie", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(MainActivity.this, "Échec de la connexion", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
