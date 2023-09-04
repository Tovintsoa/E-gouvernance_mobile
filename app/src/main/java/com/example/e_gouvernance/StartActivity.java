package com.example.e_gouvernance;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.widget.TextView;
import android.widget.Toast;

import com.example.e_gouvernance.data.ClientRepository;
import com.example.e_gouvernance.data.CommandeRepository;
import com.example.e_gouvernance.entity.ClientResponse;
import com.example.e_gouvernance.ui.utilities.Loading;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import com.example.e_gouvernance.databinding.ActivityStartBinding;
import com.google.gson.Gson;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StartActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityStartBinding binding;
    private ClientRepository clientRepository;
    private TextView resultTextView;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityStartBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.appBarStart.toolbar);
        clientRepository = new ClientRepository(this);
        Call<ClientResponse> call = clientRepository.getUserProfile();
        call.enqueue(new Callback<ClientResponse>() {
            @Override
            public void onResponse(Call<ClientResponse> call, Response<ClientResponse> response) {
                if (response.isSuccessful()) {
                    ClientResponse clientResponse = response.body();
                    if (clientResponse != null) {
                        Gson gson = new Gson();
                        TextView mailUser = findViewById(R.id.mailuser);
                        TextView usernameUser = findViewById(R.id.usernameUser);
                        mailUser.setText(clientResponse.getUser().getEmail());
                        usernameUser.setText(clientResponse.getUser().getUsername());

                        SharedPreferences sharedPreferences = getSharedPreferences("Session", MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("user", gson.toJson(clientResponse.getUser()));
                        editor.apply();

                    }
                }
            }
            @Override
            public void onFailure(Call<ClientResponse> call, Throwable t) {

            }


        });


        binding.appBarStart.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_mes_documents, R.id.nav_commande_documents, R.id.nav_proposition_document, R.id.nav_liste_document,R.id.nav_profil,R.id.QrCode)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_start);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.start, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_start);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_settings) {
            SharedPreferences sharedPreferences = getSharedPreferences("Session", MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.remove("token");
            editor.remove("user");
            editor.apply();

            Intent intent = new Intent(StartActivity.this, MainActivity.class);
            startActivity(intent);
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Loading loadingDialog = new Loading(this);
       loadingDialog.showLoadingModal();
        CommandeRepository commandeRepository = new CommandeRepository(this);
        try {
            System.out.println("ATOVEE");
            // Récupérer le résultat du scan depuis l'intent
            IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
            if (result != null) {
                if (result.getContents() != null) {
                    // Le code QR a été scanné avec succès
                    String scanResult = result.getContents();
                    System.out.println(scanResult);
                    commandeRepository.rendreCommande(scanResult).enqueue(new Callback<Void>() {
                        @Override
                        public void onResponse(Call<Void> call, Response<Void> response) {
                            if (response.isSuccessful()) {
                                Toast.makeText(StartActivity.this, "Vous avez reçu votre commande", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(StartActivity.this, "Le QR Code n'a pas pu être scané. Contacter l'administrateur.", Toast.LENGTH_SHORT).show();
                            }
                            loadingDialog.hideLoadingModal();
                        }

                        @Override
                        public void onFailure(Call<Void> call, Throwable t) {
                            t.printStackTrace();
                            loadingDialog.hideLoadingModal();
                            Toast.makeText(StartActivity.this, "Le QR Code n'a pas pu être scané. Contacter l'administrateur.", Toast.LENGTH_SHORT).show();
                        }
                    });
                } else {
                    Toast.makeText(this, "Scan annulé", Toast.LENGTH_SHORT).show();
                    loadingDialog.hideLoadingModal();
                }
            } else {
               /* resultTextView.setText("Erreur lors du scan");*/
                Toast.makeText(this, "Erreur du Scan", Toast.LENGTH_SHORT).show();
                loadingDialog.hideLoadingModal();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            Toast.makeText(this, "Erreur du Scan ici", Toast.LENGTH_SHORT).show();
            loadingDialog.hideLoadingModal();
        }
    }
}