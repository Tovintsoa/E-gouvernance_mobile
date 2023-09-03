package com.example.e_gouvernance.ui.adapter.profil;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.example.e_gouvernance.R;
import com.example.e_gouvernance.entity.DocumentResponse;
import com.example.e_gouvernance.entity.User;

import java.util.List;

public class ProfilAdapter extends ArrayAdapter<User> {
    private Context context;
    public ProfilAdapter(Context context, List<User> values) {
        super(context, R.layout.list_item_layout,values);
        this.context = context;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View customView = inflater.inflate(R.layout.fragment_profil, parent, false);

        return customView;
    }
}
