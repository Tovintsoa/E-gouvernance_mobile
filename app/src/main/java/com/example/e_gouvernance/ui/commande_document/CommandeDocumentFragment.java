package com.example.e_gouvernance.ui.commande_document;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.e_gouvernance.databinding.FragmentCommandeDocumentBinding;
import com.example.e_gouvernance.ui.adapter.ListViewAdapter;

import java.util.ArrayList;
import java.util.List;

public class CommandeDocumentFragment extends Fragment {

    private FragmentCommandeDocumentBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        CommandeDocumentViewModel galleryViewModel =
                new ViewModelProvider(this).get(CommandeDocumentViewModel.class);

        binding = FragmentCommandeDocumentBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final ListView listView = binding.listViewCommandeDocument;
        List<String> staticValues = new ArrayList<>();
        staticValues.add("Document 1");staticValues.add("Document 1");staticValues.add("Document 1");
        ListViewAdapter adapter = new ListViewAdapter(
                requireContext(),
                staticValues // La liste des valeurs statiques
        );
        listView.setAdapter(adapter);
       /* final TextView textView = binding.textGallery;
        galleryViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);*/
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}