package com.example.apitask;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.apitask.models.ParcelDelivery;
import com.google.zxing.Result;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class ScannerView extends AppCompatActivity implements ZXingScannerView.ResultHandler {

    Context context;
    ProgressDialog progressDialog;

    ZXingScannerView zXingScannerView;
    ImageView flashOn, flashOff;
    TextView TextLay;
    ParcelDelivery parcelDelievry = new ParcelDelivery();
    String Material_Code, Client_Code, Company_Code, Material_Name;
    int Material_Type, Material_IsActive;
    float One_Material_LB_Points, Material_LB_Points, Material_LB_Points_Total;
    int Material_Qty = 1;
    boolean mAutoFocus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scanner_view);

        if (savedInstanceState != null) {
            // mAutoFocus = savedInstanceState.getBoolean(AUTO_FOCUS_STATE, true);

        }

        context = ScannerView.this;

        progressDialog = new ProgressDialog(this);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.setMessage("Please Wait...");

        flashOn = findViewById(R.id.flashOn);
        flashOff = findViewById(R.id.flashOff);
        zXingScannerView = findViewById(R.id.zxing);


        TextLay = findViewById(R.id.Text);
        /// TextLay.setSelected(true);  // Set focus to the textview (moving text)
        // TextLay.setEllipsize(TextUtils.TruncateAt.MARQUEE);

        requestForCameraPermission();
    }


    @Override
    public void handleResult(Result rawResult) {

        //scanned item code
        String Code = rawResult.getText();
        // Material_Code = Code.toUpperCase();


//        Handler handler = new Handler();
//        handler.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                zXingScannerView.resumeCameraPreview(ScannerViewActivity.this);
//            }
//        }, 2000);
        Toast.makeText(context, ""+Code, Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(context, EnterChallan.class);
        intent.putExtra("code", Code);
        startActivity(intent);
        finish();

    }


    @Override
    protected void onPause() {
        super.onPause();
        zXingScannerView.stopCamera();
    }

    @Override
    protected void onResume() {
        super.onResume();
        zXingScannerView.setResultHandler(this);
        zXingScannerView.setAspectTolerance(0.2f);

        //zXingScannerView.setAutoFocus(true);
        //zXingScannerView.setSoundEffectsEnabled(true);
        requestForCameraPermission();


    }

    private void requestForCameraPermission() {
        Dexter.withContext(getApplicationContext())  //Dexter is used for runtime permission
                .withPermission(Manifest.permission.CAMERA)
                .withListener(new PermissionListener() {
                    @Override
                    public void onPermissionGranted(PermissionGrantedResponse permissionGrantedResponse) {
                        zXingScannerView.setResultHandler(ScannerView.this);
                        zXingScannerView.startCamera();
                    }

                    @Override
                    public void onPermissionDenied(PermissionDeniedResponse permissionDeniedResponse) {
                        requestForCameraPermission();
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(PermissionRequest permissionRequest, PermissionToken permissionToken) {
                        permissionToken.continuePermissionRequest();  //ask continuously for permission utill he grant permission
                    }
                }).check();
    }

    public void flashOn(View view) {

        zXingScannerView.setFlash(true);
        flashOn.setVisibility(View.GONE);
        flashOff.setVisibility(View.VISIBLE);
    }

    public void flashOff(View view) {
        zXingScannerView.setFlash(false);
        flashOn.setVisibility(View.VISIBLE);
        flashOff.setVisibility(View.GONE);
    }


    private void restartScanner() {
        zXingScannerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        zXingScannerView.resumeCameraPreview(ScannerView.this);
                    }
                }, 1000);


//                zXingScannerView.setResultHandler(ScannerViewActivity.this);
//                zXingScannerView.startCamera();
            }
        });
    }


}