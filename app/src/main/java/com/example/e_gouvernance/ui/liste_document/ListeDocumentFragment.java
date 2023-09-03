package com.example.e_gouvernance.ui.liste_document;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.example.e_gouvernance.R;
import com.example.e_gouvernance.data.ClientRepository;
import com.example.e_gouvernance.data.DocumentRepository;
import com.example.e_gouvernance.databinding.FragmentMesDocumentsBinding;
import com.example.e_gouvernance.entity.ClientResponse;
import com.example.e_gouvernance.entity.DocumentListResponse;
import com.example.e_gouvernance.entity.DocumentResponse;
import com.example.e_gouvernance.ui.adapter.ListViewAdapter;
import com.example.e_gouvernance.ui.adapter.liste_document.ListDocumentViewAdapter;
import com.example.e_gouvernance.ui.mes_documents.MesDocumentsViewModel;
import com.example.e_gouvernance.ui.utilities.Loading;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ListeDocumentFragment extends Fragment {

    private FragmentMesDocumentsBinding binding;
    Loading loadingDialog;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        ListeDocumentViewModel homeViewModel =
                new ViewModelProvider(this).get(ListeDocumentViewModel.class);

        binding = FragmentMesDocumentsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final ListView listView = binding.listView;
        loadingDialog = new Loading(getContext());
        loadingDialog.showLoadingModal();
        /**
         * appelle ws
         */
        DocumentRepository documentRepository = new DocumentRepository(getContext());
        Call<DocumentListResponse> call = documentRepository.getDocuments();
        List<DocumentResponse> staticValues = new ArrayList<>();
        call.enqueue(new Callback<DocumentListResponse>() {
            @Override
            public void onResponse(Call<DocumentListResponse> call, Response<DocumentListResponse> response) {

                if (response.isSuccessful()) {
                    DocumentListResponse documentListResponse = response.body();
                    List<DocumentResponse> documentResponses = documentListResponse.getData();
                    for (DocumentResponse documentResponse : documentResponses) {
                        staticValues.add(documentResponse);
                    }
                    ListDocumentViewAdapter adapter = new ListDocumentViewAdapter(
                            requireContext(),
                            staticValues // La liste des valeurs statiques
                    );

                    listView.setAdapter(adapter);
                    loadingDialog.hideLoadingModal();
                }
                else{
                    loadingDialog.hideLoadingModal();
                }
            }
            @Override
            public void onFailure(Call<DocumentListResponse> call, Throwable t) {
                System.out.println(t.getMessage());
                loadingDialog.hideLoadingModal();
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