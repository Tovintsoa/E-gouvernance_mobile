package com.example.e_gouvernance.ui.mes_documents;

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
import com.example.e_gouvernance.ui.adapter.ListViewAdapter;

import java.util.ArrayList;
import java.util.List;

public class MesDocumentsFragment extends Fragment {

    private FragmentMesDocumentsBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        MesDocumentsViewModel homeViewModel =
                new ViewModelProvider(this).get(MesDocumentsViewModel.class);

        binding = FragmentMesDocumentsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final ListView listView = binding.listView;
        List<String> staticValues = new ArrayList<>();
        staticValues.add("Valeur 1");staticValues.add("Valeur 1");staticValues.add("Valeur 1");


        ListViewAdapter adapter = new ListViewAdapter(
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