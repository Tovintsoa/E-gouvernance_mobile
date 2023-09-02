package com.example.e_gouvernance.ui.liste_document;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.e_gouvernance.R;
import com.example.e_gouvernance.databinding.FragmentMesDocumentsBinding;
import com.example.e_gouvernance.ui.adapter.ListViewAdapter;
import com.example.e_gouvernance.ui.mes_documents.MesDocumentsViewModel;

import java.util.ArrayList;
import java.util.List;


public class ListeDocumentFragment extends Fragment {

    private FragmentMesDocumentsBinding binding;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        ListeDocumentViewModel homeViewModel =
                new ViewModelProvider(this).get(ListeDocumentViewModel.class);

        binding = FragmentMesDocumentsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final ListView listView = binding.listView;
        List<String> staticValues = new ArrayList<>();
        staticValues.add("Doc1");staticValues.add("Doc2");staticValues.add("Doc3");


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