package com.ishwar.coronatracker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;

import android.os.Bundle;
import android.view.View;

import com.ishwar.coronatracker.databinding.ActivityHomeBinding;

public class HomeActivity extends AppCompatActivity {
    ActivityHomeBinding binding;
    NavController navController;
    AppBarConfiguration appBarConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    binding= DataBindingUtil.setContentView(this,R.layout.activity_home);
    navController= Navigation.findNavController(this,R.id.fragment);
    appBarConfiguration=new AppBarConfiguration.Builder(navController.getGraph()).build();
    binding.homeIcon.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
        navController.navigate(R.id.homeFragment);
        }
    });
    binding.vaccinationIcon.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            navController.navigate(R.id.vaccinationFragment);
        }
    });
    }
}