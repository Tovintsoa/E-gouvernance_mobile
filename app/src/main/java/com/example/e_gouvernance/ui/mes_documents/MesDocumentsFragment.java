package com.example.e_gouvernance.ui.mes_documents;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.e_gouvernance.databinding.FragmentMesDocumentsBinding;

public class MesDocumentsFragment extends Fragment {

    private FragmentMesDocumentsBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        MesDocumentsViewModel homeViewModel =
                new ViewModelProvider(this).get(MesDocumentsViewModel.class);

        binding = FragmentMesDocumentsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textHome;
        homeViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}