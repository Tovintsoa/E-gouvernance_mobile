package com.example.e_gouvernance.ui.qrCode;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.e_gouvernance.R;
import com.example.e_gouvernance.databinding.FragmentQrCodeBinding;
import com.example.e_gouvernance.ui.qrCode.QrCodeViewModel;
import com.example.e_gouvernance.ui.utilities.Loading;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class QrCodeFragment extends Fragment {

    private FragmentQrCodeBinding binding;
    private SurfaceView surfaceView;
    private Button scanButton;
    private TextView resultTextView;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        QrCodeViewModel galleryViewModel =
                new ViewModelProvider(this).get(QrCodeViewModel.class);

        binding = FragmentQrCodeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        surfaceView = binding.surfaceView;
        scanButton = root.findViewById(R.id.scanButton);


        // Set an OnClickListener for the scan button
        scanButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Show the SurfaceView when the button is clicked
                surfaceView.setVisibility(View.VISIBLE);

                // Utilize IntentIntegrator to start the QR code scanner
                IntentIntegrator integrator = new IntentIntegrator(getActivity());
                integrator.setPrompt("Scannez un code QR");
                integrator.setOrientationLocked(false);
                integrator.initiateScan();
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