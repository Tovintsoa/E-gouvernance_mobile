package com.example.e_gouvernance.ui.profil;

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
import com.example.e_gouvernance.databinding.FragmentMesDocumentsBinding;
import com.example.e_gouvernance.entity.User;
import com.example.e_gouvernance.ui.adapter.ListViewAdapter;
import com.example.e_gouvernance.ui.adapter.profil.ProfilAdapter;

import java.util.ArrayList;
import java.util.List;

public class ProfilFragment extends Fragment {

    private FragmentMesDocumentsBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        ProfilViewModel homeViewModel =
                new ViewModelProvider(this).get(ProfilViewModel.class);

        binding = FragmentMesDocumentsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final ListView listView = binding.listView;
        List<User> staticValues = new ArrayList<>();
        staticValues.add(new User());


        ProfilAdapter adapter = new ProfilAdapter(
                requireContext(),
                staticValues // La liste des valeurs statiques
        );

        listView.setAdapter(adapter);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}