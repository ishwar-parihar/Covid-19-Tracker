package com.ishwar.coronatracker.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.ishwar.coronatracker.MainActivity;
import com.ishwar.coronatracker.Modal.CountryData;
import com.ishwar.coronatracker.R;
import com.ishwar.coronatracker.databinding.CountryItemLayoutBinding;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Map;

public class CountryAdapter extends RecyclerView.Adapter<CountryAdapter.viewHolder> {
    Context context;
    ArrayList<CountryData> list;
    LayoutInflater inflater;

    public CountryAdapter(Context context, ArrayList<CountryData> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (inflater == null) {
            inflater = LayoutInflater.from(parent.getContext());
        }
        return new viewHolder(DataBindingUtil.inflate(inflater, R.layout.country_item_layout, parent, false));
    }
   public void filterList(ArrayList<CountryData> filterList){
        list=filterList;
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        CountryData countryData = list.get(position);
        holder.binding.serialNumber.setText(String.valueOf(position + 1));
        holder.binding.CountyName.setText(countryData.getCountry());
        holder.binding.totalCases.setText(NumberFormat.getInstance().format(Integer.parseInt(countryData.getCases())));
        Map<String, String> img = countryData.getCountryInfo();
        Glide.with(context).load(img.get("flag")).into(holder.binding.countryFlag);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, MainActivity.class);
                intent.putExtra("country",countryData.getCountry());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        CountryItemLayoutBinding binding;

        public viewHolder(@NonNull CountryItemLayoutBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
