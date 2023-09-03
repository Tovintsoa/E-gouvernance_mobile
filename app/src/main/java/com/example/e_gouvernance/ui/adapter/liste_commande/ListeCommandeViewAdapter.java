package com.example.e_gouvernance.ui.adapter.liste_commande;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.PopupMenu;
import com.example.e_gouvernance.R;
import com.example.e_gouvernance.data.CommandeRepository;
import com.example.e_gouvernance.data.DocumentRepository;
import com.example.e_gouvernance.entity.CommandeResponse;
import com.example.e_gouvernance.entity.DocumentUniqueResponse;
import com.example.e_gouvernance.entity.DocumentResponse;
import com.example.e_gouvernance.ui.utilities.Loading;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListeCommandeViewAdapter extends ArrayAdapter<CommandeResponse> {
    private Context context;
    private List<DocumentResponse> documents;
    Loading loadingDialog;
    private View customView;
    private TextView textView;
    private DocumentUniqueResponse documentUniqueResponse;

    public ListeCommandeViewAdapter(Context context, List<CommandeResponse> values) {
        super(context, R.layout.list_item_layout,values);
        this.context = context;

        //this.documents = values;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        customView = inflater.inflate(R.layout.list_item_layout_liste_commande, parent, false);
        textView = customView.findViewById(R.id.textViewId);
        TextView dateText = customView.findViewById(R.id.dateTextView);
        //DocumentRepository documentRepository = new DocumentRepository(getContext());

        CommandeResponse document = getItem(position);

        String idDocument = document.getId();


        ImageView imageRight = customView.findViewById(R.id.menuShow);
        imageRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                PopupMenu popupMenu = new PopupMenu(context, view);
                loadingDialog = new Loading(getContext());
                popupMenu.getMenuInflater().inflate(R.menu.menu_dropdown_liste_commande, popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        loadingDialog.showLoadingModal();
                        // Traitez le clic sur l'élément du menu ici
                        int itemId = menuItem.getItemId();

                        if (itemId == R.id.annuler) {
                            loadingDialog.hideLoadingModal();
                            return true;
                        } else {
                            loadingDialog.hideLoadingModal();
                            return false; // Handle other cases if needed
                        }
                    }
                });
                popupMenu.show();
            }
        });
        // Personnalisez votre vue en fonction de la valeur à cette position
        // Par exemple, set le texte dans un TextView dans votre layout personnalisé
        textView.setText(document.getProduit().getName());
        SimpleDateFormat isoDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        //SimpleDateFormat isoDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        SimpleDateFormat desiredDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        try {
            Date date = isoDateFormat.parse(document.getDatecommande());
            String formattedDate = desiredDateFormat.format(date);
            dateText.setText(formattedDate);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        return customView;
    }
}
