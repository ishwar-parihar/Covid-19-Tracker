package com.ishwar.coronatracker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ishwar.coronatracker.Api.ApiInterface;
import com.ishwar.coronatracker.Modal.CountryData;
import com.ishwar.coronatracker.databinding.ActivityMainBinding;

import org.eazegraph.lib.models.PieModel;

import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.ishwar.coronatracker.Api.ApiInterface.BASE_URL;

public class MainActivity extends Fragment {
    ActivityMainBinding binding;
    List<CountryData> list = new ArrayList<>();
    String country;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.activity_main, container, false);
        if (getActivity().getIntent().getStringExtra("country") != null) {
            country = getActivity().getIntent().getStringExtra("country");
        }
        binding.countryTv.setText(country);
        binding.countryTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), CountryActivity.class));
            }
        });
        Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        ApiInterface apiInterface = retrofit.create(ApiInterface.class);

        apiInterface.getCountryData().enqueue(new Callback<List<CountryData>>() {
            @Override
            public void onResponse(Call<List<CountryData>> call, Response<List<CountryData>> response) {
                list.addAll(response.body());
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        binding.mScrollView.setVisibility(View.VISIBLE);
                    }
                }, 5000);
                for (int i = 0; i < list.size(); i++) {
                    if (list.get(i).getCountry().equals(country)) ;
                    int confirm = Integer.parseInt(list.get(i).getCases());
                    int active = Integer.parseInt(list.get(i).getActive());
                    int recovered = Integer.parseInt(list.get(i).getRecovered());
                    int death = Integer.parseInt(list.get(i).getDeaths());

                    binding.totalConfirm.setText(NumberFormat.getInstance().format(confirm));
                    binding.totalActive.setText(NumberFormat.getInstance().format(active));
                    binding.totalRecovered.setText(NumberFormat.getInstance().format(recovered));
                    binding.totalDeath.setText(NumberFormat.getInstance().format(death));


                    setText(list.get(i).getUpdated());

                    binding.confirmPatientAdd.setText(NumberFormat.getInstance().format(Integer.parseInt(list.get(i).getTodayCases())));
                    binding.deathPatientAdd.setText(NumberFormat.getInstance().format(Integer.parseInt(list.get(i).getTodayDeaths())));
                    binding.recoveredPatientAdd.setText(NumberFormat.getInstance().format(Integer.parseInt(list.get(i).getTodayRecovered())));
                    binding.totalTest.setText(NumberFormat.getInstance().format(Integer.parseInt(list.get(i).getTests())));


                    binding.piechart.addPieSlice(new PieModel("Confirm", confirm, getResources().getColor(R.color.yellow)));
                    binding.piechart.addPieSlice(new PieModel("Active", active, getResources().getColor(R.color.blue)));
                    binding.piechart.addPieSlice(new PieModel("Recovered", recovered, getResources().getColor(R.color.green)));
                    binding.piechart.addPieSlice(new PieModel("Death", death, getResources().getColor(R.color.red)));
                    binding.piechart.startAnimation();
                }

            }

            @Override
            public void onFailure(Call<List<CountryData>> call, Throwable t) {
                Log.e("msg", t.getLocalizedMessage());
            }
        });
        return binding.getRoot();
    }



    @SuppressLint("SetTextI18n")
    private void setText(String updated) {
        @SuppressLint("SimpleDateFormat") DateFormat format=new SimpleDateFormat("dd/MM/yyyy");
        Long milliSecond=Long.parseLong(updated);
        Calendar calendar=Calendar.getInstance();
        calendar.setTimeInMillis(milliSecond);
        binding.covidUpdateDate.setText("Update at "+format.format(calendar.getTime()));

    }
}
