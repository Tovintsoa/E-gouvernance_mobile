package com.example.e_gouvernance.ui.adapter.liste_document;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.PopupMenu;
import com.example.e_gouvernance.R;
import com.example.e_gouvernance.data.CommandeRepository;
import com.example.e_gouvernance.entity.DocumentResponse;
import com.example.e_gouvernance.ui.utilities.Loading;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ListDocumentViewAdapter extends ArrayAdapter<DocumentResponse> {
    private Context context;
    private List<DocumentResponse> documents;
    Loading loadingDialog;
    public ListDocumentViewAdapter(Context context, List<DocumentResponse> values) {
        super(context, R.layout.list_item_layout,values);
        this.context = context;
        //this.documents = values;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View customView = inflater.inflate(R.layout.list_item_layout, parent, false);

        //String value = getItem(position);
        DocumentResponse document = getItem(position);
        String documentName = document.getName();
        String idDocument = document.getId();


        ImageView imageRight = customView.findViewById(R.id.menuShow);
        imageRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                PopupMenu popupMenu = new PopupMenu(context, view);
                loadingDialog = new Loading(getContext());
                popupMenu.getMenuInflater().inflate(R.menu.menu_dropdown_liste_document, popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        loadingDialog.showLoadingModal();
                        // Traitez le clic sur l'élément du menu ici
                        int itemId = menuItem.getItemId();

                        if (itemId == R.id.commande) {
                            String selectedDocument = idDocument;

                            Date currentDate = new Date();
                            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
                            String formattedDate = dateFormat.format(currentDate);

                            CommandeRepository commandeRepository = new CommandeRepository(getContext());
                            commandeRepository.sendCommande(selectedDocument,formattedDate).enqueue(new Callback<Void>() {
                                @Override
                                public void onResponse(Call<Void> call, Response<Void> response) {
                                    if (response.isSuccessful()) {
                                        loadingDialog.hideLoadingModal();
                                        Toast.makeText(getContext(), "Commande Effectuée", Toast.LENGTH_SHORT).show();
                                    } else {
                                        loadingDialog.hideLoadingModal();
                                        Toast.makeText(getContext(), "La commande n'a pas pu être lancé", Toast.LENGTH_SHORT).show();
                                    }
                                }

                                @Override
                                public void onFailure(Call<Void> call, Throwable t) {
                                    System.out.println(t.getMessage());
                                    loadingDialog.hideLoadingModal();
                                    Toast.makeText(getContext(), "La commande n'a pas pu être lancé", Toast.LENGTH_SHORT).show();
                                    // Une exception s'est produite pendant la requête
                                    // Vous pouvez traiter l'erreur ici ou afficher un message à l'utilisateur
                                }
                            });

                            return true;
                        } else if (itemId == R.id.menu_item_2) {
                            // Action for option 2
                            return true;
                        } else {
                            return false; // Handle other cases if needed
                        }
                    }
                });
                popupMenu.show();
            }
        });
        // Personnalisez votre vue en fonction de la valeur à cette position
        // Par exemple, set le texte dans un TextView dans votre layout personnalisé
        TextView textView = customView.findViewById(R.id.textViewId); // Remplacez textViewId par l'ID réel
        textView.setText(documentName);
        return customView;
    }
}
