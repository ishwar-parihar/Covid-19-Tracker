package com.ishwar.coronatracker.fragment;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.DatePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.ishwar.coronatracker.Modal.VaccinationModal;
import com.ishwar.coronatracker.R;
import com.ishwar.coronatracker.adapter.VaccineInfoAdapter;
import com.ishwar.coronatracker.databinding.VaccinationFragmentBinding;

import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class VaccinationFragment extends Fragment {
    VaccinationFragmentBinding binding;
    String BASE_URL = "https://cdn-api.co-vin.in/api/v2/appointment/sessions/public/calendarByPin?pincode=342001&date=25-07-2021";
    ArrayList<VaccinationModal> vaccinationList = new ArrayList<>();
    String areaPin, availableDate;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater, R.layout.vaccination_fragment, container, false);

        onClickSetup();

        return binding.getRoot();

    }


    private void onClickSetup() {
        binding.searchBtn.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(getContext());
                datePickerDialog.show();
                datePickerDialog.setOnDateSetListener(new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        Calendar calendar = Calendar.getInstance();
                        calendar.set(Calendar.YEAR, year);
                        calendar.set(Calendar.MONTH, month);
                        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
                        dateFormat.setTimeZone(calendar.getTimeZone());
                        String date = dateFormat.format(calendar.getTime());
                        setup(date);
                    }
                });

            }
        });
    }


    private void setup(String date) {
        availableDate = date;
        fetchDateNow();
    }

    private void fetchDateNow() {
        vaccinationList.clear();
        areaPin = binding.pinCodeEdt.getText().toString();
        String url_api = "https://cdn-api.co-vin.in/api/v2/appointment/sessions/public/calendarByPin?pincode=" + areaPin + "&date=" + availableDate;
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url_api, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject object = new JSONObject(response);
                    JSONArray centerArray = object.getJSONArray("centers");
                    if (centerArray.length()==0){
                        Toast.makeText(getContext(), "Vaccine not available", Toast.LENGTH_SHORT).show();
                    }else {


                        for (int i = 0; i < centerArray.length(); i++) {
                            JSONObject centerObject = centerArray.getJSONObject(i);
                            VaccinationModal vaccinationModal = new VaccinationModal();

                            vaccinationModal.setVaccineCenter(centerObject.getString("name"));
                            vaccinationModal.setVaccineCenterAddress(centerObject.getString("address"));
                            vaccinationModal.setVaccineTimings(centerObject.getString("from"));
                            vaccinationModal.setVaccineCenterTime(centerObject.getString("to"));
                            vaccinationModal.setVaccinationCharges(centerObject.getString("fee_type"));
                            vaccinationModal.setCity(centerObject.getString("district_name"));

                            JSONObject sessionsObject = centerObject.getJSONArray("sessions").getJSONObject(0);
                            vaccinationModal.setVaccineAge(sessionsObject.getString("min_age_limit"));
                            vaccinationModal.setVaccineAvailable(sessionsObject.getString("available_capacity"));
                            vaccinationModal.setVaccineName(sessionsObject.getString("vaccine"));
                            vaccinationList.add(vaccinationModal);

                        }
                    }
                    VaccineInfoAdapter adapter = new VaccineInfoAdapter(vaccinationList, getContext());
                    binding.vaccinationDetailsRecycleView.setAdapter(adapter);

                } catch (Exception e) {
                    e.printStackTrace();
                }


            }


        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(stringRequest);
    }
}

