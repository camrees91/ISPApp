package com.isp.app.ui.info

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.isp.app.databinding.FragmentInfoBinding

class InfoFragment : Fragment() {
    private var _binding: FragmentInfoBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentInfoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Set developer and app info
        binding.infoText.text = "Developers: Cameron Rees, Joshua Shears\nApp Version: 1.0\nInstitute: ISP"
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
} 