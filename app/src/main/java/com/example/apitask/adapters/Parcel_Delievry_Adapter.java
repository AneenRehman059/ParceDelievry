package com.example.apitask.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.apitask.ApiClient;
import com.example.apitask.CustomToastError;
import com.example.apitask.R;
import com.example.apitask.UpdateDelievryRequest;
import com.example.apitask.UpdateDelievryResponse;
import com.example.apitask.models.ParcelDelivery;
import com.example.apitask.room.MyDatabase;
import com.example.apitask.utils.Utills;


import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Parcel_Delievry_Adapter extends RecyclerView.Adapter<Parcel_Delievry_Adapter.ViewHolder> {
    Context context;
    List<ParcelDelivery> parcelDeliveries;
    String code = "";

    public Parcel_Delievry_Adapter(Context context, List<ParcelDelivery> parcelDeliveries) {
        this.context = context;
        this.parcelDeliveries = parcelDeliveries;
    }


    @NonNull
    @Override

    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.challan_list_design, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        ParcelDelivery parcelDelivery = parcelDeliveries.get(position);
        holder.challan_time.setText(parcelDelivery.getDelievryTime());
        holder.parcel_no.setText(parcelDelivery.getParcelNo());
        holder.status.setText(parcelDelivery.getDelievryStatus());
        holder.reason_not_deliever.setText(parcelDelivery.getReasonNotDeliever());

        code = parcelDeliveries.get(position).getParcelNo();
        holder.post_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(context, "Post Seccussfuly", Toast.LENGTH_SHORT).show();
                UpdateDelievryRequest updateDelievryRequest = new UpdateDelievryRequest();
                updateDelievryRequest.setChallanCODE(parcelDelivery.getParcelNo());
                updateDelievryRequest.setDelievryREMARKS(parcelDelivery.getDelievryStatus());
                updateDelievryRequest.setDelieverdReason(parcelDelivery.getReasonNotDeliever());

                if (!Utills.isConnectingToInternet(context)){
                    new CustomToastError().Show_Toast(context,view,"Please make sure your device is connected with internet");
                return;
                }

                Call<UpdateDelievryResponse> call = ApiClient.getUserService().delievryParcel(updateDelievryRequest);
                call.enqueue(new Callback<UpdateDelievryResponse>() {
                    @SuppressLint("NotifyDataSetChanged")
                    @Override
                    public void onResponse(Call<UpdateDelievryResponse> call, Response<UpdateDelievryResponse> response) {
                        if (response.isSuccessful()) {
                            Toast.makeText(context, "Data Added Successfuly", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(context, "Data Not Added", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<UpdateDelievryResponse> call, Throwable t) {
                        Toast.makeText(context, "Throwable" + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

                MyDatabase.getDbInstance(context).noteDao().deleteNotes(parcelDeliveries.get(position));

//                holder.designLayout.setVisibility(View.GONE);
            }
        });

    }

    @Override
    public int getItemCount() {
        return parcelDeliveries.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView challan_time, parcel_no, status,reason_not_deliever;
        Button post_btn;
        LinearLayout designLayout, why;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            post_btn = itemView.findViewById(R.id.btn_post);
            challan_time = itemView.findViewById(R.id.challan_time);
            parcel_no = itemView.findViewById(R.id.parccel_no);
            status = itemView.findViewById(R.id.status);
            reason_not_deliever = itemView.findViewById(R.id.Nodel_reason);
            why = itemView.findViewById(R.id.why);
            designLayout = itemView.findViewById(R.id.design_layout);
        }
    }
}
