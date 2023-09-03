package com.example.e_gouvernance.ui.profil;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.e_gouvernance.R;
import com.example.e_gouvernance.data.ClientRepository;
import com.example.e_gouvernance.databinding.FragmentMesDocumentsBinding;
import com.example.e_gouvernance.databinding.FragmentProfilBinding;
import com.example.e_gouvernance.entity.ClientResponse;
import com.example.e_gouvernance.ui.adapter.profil.ProfilAdapter;
import com.example.e_gouvernance.ui.utilities.Loading;


import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfilFragment extends Fragment {

    private FragmentProfilBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        ProfilViewModel homeViewModel =
                new ViewModelProvider(this).get(ProfilViewModel.class);
        View view = inflater.inflate(R.layout.fragment_profil, container, false);
        Loading loadingDialog = new Loading(getContext());
        loadingDialog.showLoadingModal();
        binding = FragmentProfilBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        System.out.println("aaaaa");
        final LinearLayout listView = binding.profilView;
        ClientRepository clientRepository = new ClientRepository(getContext());
        Call<ClientResponse> call = clientRepository.getUserProfile();

        call.enqueue(new Callback<ClientResponse>() {
            @Override
            public void onResponse(Call<ClientResponse> call, Response<ClientResponse> response) {
                if (response.isSuccessful()) {
                    CardView myCardView = view.findViewById(R.id.cardView);
                    loadingDialog.hideLoadingModal();
                    ClientResponse clientResponse = response.body();
                    TextView mail =  listView.findViewById(R.id.emailTextView);
                    TextView nameTextView = listView.findViewById(R.id.nameTextView);
                    mail.setText(clientResponse.getUser().getEmail());
                    nameTextView.setText(clientResponse.getUser().getUsername());
                    myCardView.setVisibility(View.VISIBLE);
                }
                else{
                    loadingDialog.hideLoadingModal();
                    System.out.println("TSY AIZA");
                }
            }

            @Override
            public void onFailure(Call<ClientResponse> call, Throwable t) {
                loadingDialog.hideLoadingModal();
                System.out.println(t.getMessage());
            }
        });


        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}