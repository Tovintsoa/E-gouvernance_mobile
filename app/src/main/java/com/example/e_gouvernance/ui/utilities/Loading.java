package com.example.e_gouvernance.ui.utilities;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ProgressBar;

import com.example.e_gouvernance.R;

public class Loading {
    private Dialog loadingDialog;
    public Loading(Context context){
        loadingDialog = new Dialog(context);
        View dialogView = LayoutInflater.from(context).inflate(R.layout.loading_dialog, null);
        ProgressBar progressBar = dialogView.findViewById(R.id.progressBar);
        loadingDialog.setContentView(dialogView);
        loadingDialog.setCancelable(false);
        loadingDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
    }
    public void createDialog(Context context){

    }
    public void showLoadingModal() {
        if (loadingDialog != null && !loadingDialog.isShowing()) {
            loadingDialog.show();
        }
    }

    // Utilisez cette méthode pour masquer le modal de chargement
    public void hideLoadingModal() {
        if (loadingDialog != null && loadingDialog.isShowing()) {
            loadingDialog.dismiss();
        }
    }
}
