package com.example.e_gouvernance.ui.mes_documents;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MesDocumentsViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public MesDocumentsViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Liste de Mes documents");
    }

    public LiveData<String> getText() {
        return mText;
    }
}