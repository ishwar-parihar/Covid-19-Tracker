package com.ishwar.coronatracker.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.ishwar.coronatracker.R;
import com.ishwar.coronatracker.databinding.VaccinationFragmentBinding;

public class VaccinationFragment extends Fragment {
    VaccinationFragmentBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding=DataBindingUtil.inflate(inflater, R.layout.vaccination_fragment,container,false);
        return binding.getRoot();
    }
}
