package com.example.e_gouvernance.ui.commande_document;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.e_gouvernance.databinding.FragmentCommandeDocumentBinding;

public class CommandeDocumentFragment extends Fragment {

    private FragmentCommandeDocumentBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        CommandeDocumentViewModel galleryViewModel =
                new ViewModelProvider(this).get(CommandeDocumentViewModel.class);

        binding = FragmentCommandeDocumentBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textGallery;
        galleryViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}