package com.example.e_gouvernance.ui.commande_document;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class CommandeDocumentViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public CommandeDocumentViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Formulaire commande document");
    }

    public LiveData<String> getText() {
        return mText;
    }
}