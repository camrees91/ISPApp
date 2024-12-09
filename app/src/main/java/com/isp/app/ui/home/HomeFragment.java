package com.isp.app.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import com.isp.app.R;
import com.isp.app.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private HomeViewModel homeViewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        binding.setViewModel(homeViewModel);
        binding.setLifecycleOwner(this);

        setupButtonListeners();

        return binding.getRoot();
    }

    private void setupButtonListeners() {
        NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment);

        binding.buttonViewPrograms.setOnClickListener(v -> 
            navController.navigate(R.id.action_homeFragment_to_programsFragment));

        binding.buttonViewSchedule.setOnClickListener(v -> 
            navController.navigate(R.id.action_homeFragment_to_scheduleFragment));

        binding.buttonViewCalendar.setOnClickListener(v -> 
            navController.navigate(R.id.action_homeFragment_to_calendarFragment));

        binding.buttonViewTransit.setOnClickListener(v -> 
            navController.navigate(R.id.action_homeFragment_to_transitFragment));

        binding.buttonViewNews.setOnClickListener(v -> 
            navController.navigate(R.id.action_homeFragment_to_newsFragment));

        binding.buttonViewContacts.setOnClickListener(v -> 
            navController.navigate(R.id.action_homeFragment_to_contactsFragment));

        binding.buttonViewInfo.setOnClickListener(v -> 
            navController.navigate(R.id.action_homeFragment_to_infoFragment));

        binding.buttonViewNotes.setOnClickListener(v -> 
            navController.navigate(R.id.action_homeFragment_to_noteFragment));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
} 