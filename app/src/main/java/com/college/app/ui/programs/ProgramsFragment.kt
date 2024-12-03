package com.college.app.ui.programs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.college.app.databinding.FragmentProgramsBinding
import com.college.app.ui.programs.adapter.ProgramsAdapter

class ProgramsFragment : Fragment() {
    private var _binding: FragmentProgramsBinding? = null
    private val binding get() = _binding!!
    private val viewModel: ProgramsViewModel by viewModels()
    private val programsAdapter = ProgramsAdapter { program ->
        findNavController().navigate(
            ProgramsFragmentDirections.actionProgramsToCourses(program.id)
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProgramsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        observeViewModel()
        viewModel.loadPrograms()
    }

    private fun setupRecyclerView() {
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = programsAdapter
        }
    }

    private fun observeViewModel() {
        viewModel.programs.observe(viewLifecycleOwner) { programs ->
            programsAdapter.submitList(programs)
        }

        viewModel.loading.observe(viewLifecycleOwner) { isLoading ->
            binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
        }

        viewModel.error.observe(viewLifecycleOwner) { error ->
            error?.let {
                // Show error message
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
} 