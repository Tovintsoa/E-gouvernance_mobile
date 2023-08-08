package com.example.e_gouvernance.ui.suggestion_documents;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SUggestionDocumentViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public SUggestionDocumentViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Document suggéré");
    }

    public LiveData<String> getText() {
        return mText;
    }
}