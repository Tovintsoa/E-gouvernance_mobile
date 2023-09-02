package com.example.e_gouvernance.ui.profil;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ProfilViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public ProfilViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Liste de Mes documents");
    }

    public LiveData<String> getText() {
        return mText;
    }
}