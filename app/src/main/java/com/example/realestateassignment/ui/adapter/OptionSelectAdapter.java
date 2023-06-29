package com.example.realestateassignment.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.realestateassignment.Interface.Datatransfer;
import com.example.realestateassignment.R;
import com.example.realestateassignment.model.Option;

import java.util.ArrayList;

public class OptionSelectAdapter extends RecyclerView.Adapter<OptionSelectAdapter.OptionSelectViewHolder>{
    private FacilityAdapter.FacilityViewHolder holder1;
    private ArrayList<Option> options;
    private Datatransfer datatransfer;
    private Context context;

    public static class OptionSelectViewHolder extends RecyclerView.ViewHolder {
        private TextView tvOption;
        private LinearLayout llContainer;

        public OptionSelectViewHolder(View itemView) {
            super(itemView);
            tvOption = itemView.findViewById(R.id.tvOption);
            llContainer = itemView.findViewById(R.id.llContainer);
        }
    }

    public OptionSelectAdapter(Context context, ArrayList<Option> options, FacilityAdapter.FacilityViewHolder holder, Datatransfer datatransfer) {
        this.options = options;
        this.datatransfer = datatransfer;
        this.context = context;
        holder1 = holder;
    }

    @Override
    public OptionSelectAdapter.OptionSelectViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_option_select, parent, false);
        return new OptionSelectAdapter.OptionSelectViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final OptionSelectAdapter.OptionSelectViewHolder holder, int position) {
        holder.tvOption.setText(options.get(position).getName());
        holder.llContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datatransfer.onOptionSelect(holder.getAdapterPosition(),holder1);
            }
        });
    }

    @Override
    public int getItemCount() {
        return options.size();
    }
}
