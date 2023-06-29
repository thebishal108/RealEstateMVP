package com.example.realestateassignment.ui.adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.realestateassignment.Interface.Datatransfer;
import com.example.realestateassignment.R;
import com.example.realestateassignment.model.Option;
import com.example.realestateassignment.model.RealEstateListings;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.ArrayList;

public class FacilityAdapter extends RecyclerView.Adapter<FacilityAdapter.FacilityViewHolder>{
    private ArrayList<RealEstateListings> realEstateListings;
    private Datatransfer datatransfer;
    private Context context;
    private BottomSheetDialog optionSelectVehicleDialog;
    private OptionSelectAdapter optionSelectAdapter;

    public static class FacilityViewHolder extends RecyclerView.ViewHolder {
        public ImageView ivImage;
        public TextView tvName;
        public TextView tvOptions;

        public FacilityViewHolder(View itemView) {
            super(itemView);
            ivImage = itemView.findViewById(R.id.ivImage);
            tvName = itemView.findViewById(R.id.tvName);
            tvOptions = itemView.findViewById(R.id.tvOptions);
        }
    }

    public FacilityAdapter(Context context,ArrayList<RealEstateListings> realEstateListings, Datatransfer datatransfer) {
        this.realEstateListings = realEstateListings;
        this.datatransfer = datatransfer;
        this.context = context;
    }

    @Override
    public FacilityViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_facility_layout, parent, false);
        return new FacilityViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final FacilityViewHolder holder, int position) {
        holder.tvName.setText(realEstateListings.get(position).getFacility().getName());
        if(realEstateListings.get(position).getSelectedOption()!=null){
            holder.tvOptions.setText(realEstateListings.get(position).getSelectedOption().getName());
        }else{
            holder.tvOptions.setText(R.string.select_option);
        }
        holder.tvOptions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText etSearch;
                ImageView ivClose;
                TextView tvDialogHeader;
                RecyclerView rvItemSelect;
                OptionSelectAdapter optionSelectAdapter;
                optionSelectVehicleDialog = new BottomSheetDialog(context,R.style.BottomSheetDialogTheme);
                optionSelectVehicleDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                View bottomSheetView = LayoutInflater.from(context).inflate(R.layout.dialog_option_item_selection, null);
                optionSelectVehicleDialog.setContentView(bottomSheetView);
                BottomSheetBehavior bottomSheetBehavior = BottomSheetBehavior.from((View) bottomSheetView.getParent());
                bottomSheetBehavior.setPeekHeight(ViewGroup.LayoutParams.MATCH_PARENT);
                TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,500,context.getResources().getDisplayMetrics());
                tvDialogHeader = optionSelectVehicleDialog.findViewById(R.id.tvDialogHeader);
                ivClose = optionSelectVehicleDialog.findViewById(R.id.ivClose);
                rvItemSelect = optionSelectVehicleDialog.findViewById(R.id.rvItemSelect);
                tvDialogHeader.setText("Select Vehicle");
                LinearLayoutManager vehicleLayoutManager = new LinearLayoutManager(context);
                rvItemSelect.setLayoutManager(vehicleLayoutManager);
                optionSelectAdapter = new OptionSelectAdapter(context, (ArrayList<Option>) realEstateListings.get(holder.getAdapterPosition()).getFacility().getOptions(),holder,datatransfer);
                rvItemSelect.setAdapter(optionSelectAdapter);
                ivClose.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        optionSelectVehicleDialog.dismiss();
                    }
                });
                optionSelectVehicleDialog.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return realEstateListings.size();
    }
    public void dismissDialog() {
        optionSelectVehicleDialog.dismiss();
    }
}
