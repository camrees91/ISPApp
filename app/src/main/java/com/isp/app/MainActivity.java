package com.isp.app;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentContainerView;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import com.isp.app.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private AppBarConfiguration appBarConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        FragmentContainerView navHostFragment = findViewById(R.id.nav_host_fragment);
        NavController navController = ((NavHostFragment) getSupportFragmentManager().findFragmentById(navHostFragment.getId())).getNavController();

        appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.programsFragment,
                R.id.scheduleFragment,
                R.id.calendarFragment,
                R.id.transitFragment,
                R.id.newsFragment,
                R.id.contactsFragment,
                R.id.infoFragment
        ).setOpenableLayout(binding.drawerLayout).build();

        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navigationView, navController);
    }

    @Override
    public boolean onSupportNavigateUp() {
        FragmentContainerView navHostFragment = findViewById(R.id.nav_host_fragment);
        NavController navController = ((NavHostFragment) getSupportFragmentManager().findFragmentById(navHostFragment.getId())).getNavController();
        return NavigationUI.navigateUp(navController, appBarConfiguration) || super.onSupportNavigateUp();
    }
} 