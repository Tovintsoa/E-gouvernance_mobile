package com.example.e_gouvernance.ui.qrCode;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class QrCodeViewModel extends ViewModel {
    private final MutableLiveData<String> mText;

    public QrCodeViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("QR Code");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
