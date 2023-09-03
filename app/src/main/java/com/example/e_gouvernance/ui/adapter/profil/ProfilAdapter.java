package com.example.e_gouvernance.ui.adapter.profil;

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
import com.example.e_gouvernance.entity.ClientResponse;
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

public class ProfilAdapter extends ArrayAdapter<ClientResponse> {
    private Context context;
    private List<DocumentResponse> documents;
    Loading loadingDialog;
    public ProfilAdapter(Context context, List<ClientResponse> values) {
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
