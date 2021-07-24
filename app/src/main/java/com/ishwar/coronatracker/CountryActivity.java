package com.ishwar.coronatracker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;

import com.ishwar.coronatracker.Api.ApiInterface;
import com.ishwar.coronatracker.Modal.CountryData;
import com.ishwar.coronatracker.adapter.CountryAdapter;
import com.ishwar.coronatracker.databinding.ActivityCountryBinding;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.ishwar.coronatracker.Api.ApiInterface.BASE_URL;

public class CountryActivity extends AppCompatActivity {
    ActivityCountryBinding binding;
    ArrayList<CountryData> list = new ArrayList<>();
    CountryAdapter countryAdapter;
    String country = "India";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_country);

        countryAdapter = new CountryAdapter(this, list);
        Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        ApiInterface apiInterface = retrofit.create(ApiInterface.class);
        binding.countryRecycleView.setAdapter(countryAdapter);
        apiInterface.getCountryData().enqueue(new Callback<List<CountryData>>() {
            @Override
            public void onResponse(Call<List<CountryData>> call, Response<List<CountryData>> response) {
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        binding.mPrograssBar.setVisibility(View.GONE);
                        binding.countryRecycleView.setVisibility(View.VISIBLE);

                    }
                }, 5000);
                list.addAll(response.body());
                countryAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<CountryData>> call, Throwable t) {
                Log.e("Response", t.getLocalizedMessage());

            }
        });
        binding.searchCountryEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                filter(s.toString());
            }
        });


    }

    private void filter(String Text) {
        ArrayList<CountryData> filterList = new ArrayList<>();
        for (CountryData items : list) {
            if (items.getCountry().toLowerCase().contains(Text.toLowerCase())) {
                filterList.add(items);
            }
        }
        countryAdapter.filterList(filterList);
    }
}