package com.example.e_gouvernance.ui.adapter;

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

import java.util.List;

public class ListViewAdapter extends ArrayAdapter<String> {
    private Context context;
    public ListViewAdapter(Context context, List<String> values) {
        super(context, R.layout.list_item_layout, values);
        this.context = context;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View customView = inflater.inflate(R.layout.list_item_layout, parent, false);

        String value = getItem(position);
        ImageView imageRight = customView.findViewById(R.id.menuShow);
        imageRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popupMenu = new PopupMenu(context, view);
                popupMenu.getMenuInflater().inflate(R.menu.menu_dropdown_document, popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        // Traitez le clic sur l'élément du menu ici
                        int itemId = menuItem.getItemId();

                        if (itemId == R.id.menu_item_1) {
                            // Action for option 1
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
        textView.setText(value);
        return customView;
    }
}
