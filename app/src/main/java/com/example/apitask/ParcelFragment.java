package com.example.apitask;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;

import com.example.apitask.adapters.Parcel_Delievry_Adapter;
import com.example.apitask.models.ParcelDelivery;
import com.example.apitask.room.MyDatabase;

import java.util.ArrayList;
import java.util.List;

public class ParcelFragment extends Fragment {
    SQLiteDatabase sqLiteDatabase;

    RecyclerView recyclerView;
    Parcel_Delievry_Adapter parcel_delievry_adapter;
    List<ParcelDelivery> parcels = new ArrayList<>();
    LinearLayout scan;
    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_parcel, container, false);


        scan = (LinearLayout) view.findViewById(R.id.btnFuelly);
        recyclerView = view.findViewById(R.id.rv_challan);
//        Animation animation = AnimationUtils.loadAnimation(getContext(), R.anim.bounce_animation);
//        scan.startAnimation(animation);

//        displayData();
        parcel_delievry_adapter = new Parcel_Delievry_Adapter(getContext(), parcels);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,true);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(parcel_delievry_adapter);

        MyDatabase.getDbInstance(getContext()).noteDao().getAllNotesLive().observe(getViewLifecycleOwner(), getparcel -> {
            parcels.clear();
            parcels.addAll(getparcel);
            parcel_delievry_adapter.notifyDataSetChanged();
        });
//        scan.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getContext(), EnterChallan.class);
//                startActivity(intent);
//            }
//        });
        return view;
    }


}