package com.ishwar.coronatracker.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.ishwar.coronatracker.Modal.VaccinationModal;
import com.ishwar.coronatracker.R;
import com.ishwar.coronatracker.databinding.VaccinationInfoLayoutBinding;

import java.util.ArrayList;

public class VaccineInfoAdapter extends RecyclerView.Adapter<VaccineInfoAdapter.viewHolder> {
    LayoutInflater inflater;
    ArrayList<VaccinationModal> vaccinationList;
    Context context;


    public VaccineInfoAdapter(ArrayList<VaccinationModal> vaccinationList, Context context) {
        this.vaccinationList = vaccinationList;
        this.context = context;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (inflater==null){
           inflater=LayoutInflater.from(parent.getContext());
        }
        return new viewHolder(DataBindingUtil.inflate(inflater,R.layout.vaccination_info_layout,parent,false));
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
    holder.binding.vaccinationName.setText(vaccinationList.get(position).getVaccineName());
    holder.binding.vaccinationAge.setText(vaccinationList.get(position).getVaccineAge());
    holder.binding.vaccinationAvailable.setText(vaccinationList.get(position).getVaccineAvailable());
    holder.binding.vaccinationCenter.setText(vaccinationList.get(position).getVaccineCenter());
    holder.binding.vaccinationCharges.setText(vaccinationList.get(position).getVaccinationCharges());
    holder.binding.vaccinationTime.setText(vaccinationList.get(position).getVaccineTimings() + " - " +vaccinationList.get(position).getVaccineCenterTime());
    holder.binding.vaccinationLocation.setText(vaccinationList.get(position).getVaccineCenterAddress());
    holder.binding.vaccinationCity.setText(vaccinationList.get(position).getCity());

    }

    @Override
    public int getItemCount() {
        return vaccinationList.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        VaccinationInfoLayoutBinding binding;
        public viewHolder(@NonNull  VaccinationInfoLayoutBinding binding) {
            super(binding.getRoot());
            this.binding=binding;
        }
    }
}
