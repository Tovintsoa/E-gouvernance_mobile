package com.example.e_gouvernance.ui.liste_document;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ListeDocumentViewModel extends ViewModel {
    private final MutableLiveData<String> mText;

    public ListeDocumentViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Liste des documents");
    }

    public ListeDocumentViewModel(MutableLiveData<String> mText) {
        this.mText = mText;
    }

    public LiveData<String> getText() {
        return mText;
    }
}
