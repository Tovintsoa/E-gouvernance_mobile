package com.example.e_gouvernance.ui.suggestion_documents;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.e_gouvernance.databinding.FragmentSuggestionDocumentBinding;

public class SuggestionDocumentFragment extends Fragment {

    private FragmentSuggestionDocumentBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        SUggestionDocumentViewModel slideshowViewModel =
                new ViewModelProvider(this).get(SUggestionDocumentViewModel.class);

        binding = FragmentSuggestionDocumentBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textSlideshow;
        slideshowViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}