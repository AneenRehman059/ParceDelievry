package com.example.apitask;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.apitask.models.ParcelDelivery;
import com.example.apitask.room.MyDatabase;
import com.example.apitask.room.NoteDao;
import com.example.apitask.utils.Utills;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class EnterChallan extends AppCompatActivity implements View.OnClickListener {
    List<ParcelDelivery> parcelDeliveryArrayList = new ArrayList<>();
    EditText qr_code;
    RadioButton button0, button1;
    RadioGroup radioGroup;
    LinearLayout layout, remark_layout;
    ImageButton btnChallan;
    Button btn_capture, btn_submit;
    Spinner reason;
    int id;
    String code_qr;
    String current_dateTime;
    SQLiteDatabase sqLiteDatabase;
    Context context = this;
    public static String currentDateTime = "00:00";
    Cursor cursor;
    private static boolean isDelivered = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_challan);


        qr_code = findViewById(R.id.et_search);
        button0 = findViewById(R.id.radio0);
        button1 = findViewById(R.id.radio1);
        radioGroup = findViewById(R.id.radioGroup);
        reason = findViewById(R.id.reason);
        layout = findViewById(R.id.complain_layout);
        remark_layout = findViewById(R.id.remark_layout);
        btnChallan = findViewById(R.id.btnScanChallan);
        btn_capture = findViewById(R.id.btn_capture);
        btn_submit = findViewById(R.id.submit_btn);

        btn_submit.setOnClickListener(this);
        btn_capture.setOnClickListener(this);

        ParcelDelivery parcelDelivery = new ParcelDelivery();

        btnChallan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                scanCode();
                Intent intent = new Intent(EnterChallan.this, ScannerView.class);
                startActivity(intent);
            }
        });

        String s = getIntent().getStringExtra("code");
            qr_code.setText(s);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                switch (i) {
                    case R.id.radio0:
                        remark_layout.setVisibility(View.VISIBLE);
                        isDelivered = true;
                        parcelDelivery.setDelievryStatus("Delivered");
                        // Write code here
                        break;
                    case R.id.radio1:
                        remark_layout.setVisibility(View.VISIBLE);
                        layout.setVisibility(View.VISIBLE);
                        isDelivered = false;
                        parcelDelivery.setDelievryStatus("Reason");
                        break;
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_capture) {
            if (ContextCompat.checkSelfPermission(this,
                    Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this,
                        new String[]{
                                Manifest.permission.CAMERA
                        }, 100);
            }
            btn_capture.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(intent, 100);
                }
            });
        } else if (v.getId() == R.id.submit_btn) {

            MyDatabase db = MyDatabase.getDbInstance(this.context);
            ParcelDelivery parcelDelievry = new ParcelDelivery();

            if (!isDelivered) {
                parcelDelievry.setDelievryStatus("Not Delivered");
                parcelDelievry.setReasonNotDeliever(reason.getSelectedItem().toString());

            } else {
                parcelDelievry.setDelievryStatus("Delivered");
            }


            NoteDao noteDao = db.noteDao();
            Boolean check = noteDao.is_exists(qr_code.getText().toString());
            if (!check){
                parcelDelievry.setParcelNo(qr_code.getText().toString());
                parcelDelievry.setDelievryTime((Utills.getTimeString(System.currentTimeMillis())));
                db.noteDao().insertNotes(parcelDelievry);
            }
            else {
                Toast.makeText(context, "Exists", Toast.LENGTH_SHORT).show();
                return;
            }

            getSupportFragmentManager().beginTransaction()
                    .add(android.R.id.content, new ParcelFragment()).commit();
        }
    }
}
