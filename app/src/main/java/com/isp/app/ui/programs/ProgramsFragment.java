package com.isp.app.ui.programs;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.isp.app.databinding.FragmentProgramsBinding;
import com.isp.app.ui.programs.adapter.ProgramsAdapter;

public class ProgramsFragment extends Fragment {
    private FragmentProgramsBinding binding;
    private ProgramsViewModel viewModel;
    private ProgramsAdapter programsAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentProgramsBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(this).get(ProgramsViewModel.class);
        programsAdapter = new ProgramsAdapter(program -> {
            NavHostFragment.findNavController(ProgramsFragment.this)
                    .navigate(ProgramsFragmentDirections.actionProgramsToCourses(program.getId()));
        });

        setupRecyclerView();
        observeViewModel();
        viewModel.loadPrograms();
    }

    private void setupRecyclerView() {
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.recyclerView.setAdapter(programsAdapter);
    }

    private void observeViewModel() {
        viewModel.getPrograms().observe(getViewLifecycleOwner(), programs -> programsAdapter.submitList(programs));
        viewModel.getLoading().observe(getViewLifecycleOwner(), isLoading -> binding.progressBar.setVisibility(isLoading ? View.VISIBLE : View.GONE));
        viewModel.getError().observe(getViewLifecycleOwner(), error -> {
            if (error != null) {
                // Show error message
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
} 