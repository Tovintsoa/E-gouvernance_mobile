package com.example.e_gouvernance.ui.mes_documents;

import static com.example.e_gouvernance.data.sqlLite.CommandeDatabaseHelper.TABLE_NAME;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.e_gouvernance.R;
import com.example.e_gouvernance.data.CommandeRepository;
import com.example.e_gouvernance.data.DocumentRepository;
import com.example.e_gouvernance.data.sqlLite.CommandeDatabaseHelper;
import com.example.e_gouvernance.databinding.FragmentMesDocumentsBinding;
import com.example.e_gouvernance.entity.CommandeListResponse;
import com.example.e_gouvernance.entity.CommandeResponse;
import com.example.e_gouvernance.entity.DocumentListResponse;
import com.example.e_gouvernance.entity.DocumentResponse;
import com.example.e_gouvernance.entity.sqlLite.Commande;
import com.example.e_gouvernance.ui.adapter.ListViewAdapter;
import com.example.e_gouvernance.ui.adapter.liste_commande.ListeCommandeViewAdapter;
import com.example.e_gouvernance.ui.adapter.liste_document.ListDocumentViewAdapter;
import com.example.e_gouvernance.ui.utilities.Loading;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MesDocumentsFragment extends Fragment {

    private FragmentMesDocumentsBinding binding;
    Loading loadingDialog;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        MesDocumentsViewModel homeViewModel =
                new ViewModelProvider(this).get(MesDocumentsViewModel.class);

        binding = FragmentMesDocumentsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        loadingDialog = new Loading(getContext());
        loadingDialog.showLoadingModal();
        final ListView listView = binding.listView;

        /**
         * appelle ws
         */
        CommandeRepository commandeRepository = new CommandeRepository(getContext());
        Call<CommandeListResponse> call = commandeRepository.getMyCommande();
        List<CommandeResponse> staticValues = new ArrayList<>();
        System.out.println("ETO VE ISIKA");
        CommandeDatabaseHelper dbHelper = new CommandeDatabaseHelper(getContext());
        List<Commande> commande = dbHelper.getAllCommandes();
        ListeCommandeViewAdapter adapter = new ListeCommandeViewAdapter(
                requireContext(),
                commande // La liste des valeurs statiques
        );



        if (isOnline()) {
            call.enqueue(new Callback<CommandeListResponse>() {
                @Override
                public void onResponse(Call<CommandeListResponse> call, Response<CommandeListResponse> response) {

                    if (response.isSuccessful()) {

                        CommandeListResponse commandeListResponse = response.body();
                        List<CommandeResponse> commandeResponses = commandeListResponse.getData();
                     /*for (CommandeResponse commandeResponse : commandeResponses) {
                        if(commandeResponse.getProduit() != null) {
                            staticValues.add(commandeResponse);
                        }
                    }*/

                        CommandeDatabaseHelper dbHelper = new CommandeDatabaseHelper(getContext());
                        SQLiteDatabase db = dbHelper.getWritableDatabase();
                        db.delete(TABLE_NAME, null, null);
                        for (CommandeResponse commandeResponse : commandeResponses) {
                            ContentValues values = new ContentValues();
                            values.put("_id", commandeResponse.getId());
                            values.put("produit", commandeResponse.getProduit().getName());
                            values.put("user", commandeResponse.getUser());
                            values.put("datecommande", commandeResponse.getDatecommande());
                            values.put("etat", commandeResponse.getEtat());

                            db.insert(TABLE_NAME, null, values);
                        }
                        db.close();
                        List<Commande> commande = dbHelper.getAllCommandes();
                        ListeCommandeViewAdapter adapter = new ListeCommandeViewAdapter(
                                requireContext(),
                                commande // La liste des valeurs statiques
                        );

                        listView.setAdapter(adapter);
                        loadingDialog.hideLoadingModal();
                    } else {
                        System.out.println("sa ici");
                        loadingDialog.hideLoadingModal();
                    }
                }

                @Override
                public void onFailure(Call<CommandeListResponse> call, Throwable t) {
                    System.out.println("errer");
                    System.out.println(t.getMessage());
                    loadingDialog.hideLoadingModal();
                }

            });
        }
        else{
            loadingDialog.hideLoadingModal();
            listView.setAdapter(adapter);
        }

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
    // Vérifiez la connectivité réseau
    private boolean isOnline() {
        ConnectivityManager connectivityManager = (ConnectivityManager) requireContext().getSystemService(Context.CONNECTIVITY_SERVICE);

        if (connectivityManager != null) {
            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
            return networkInfo != null && networkInfo.isConnectedOrConnecting();
        }

        return false;
    }

}