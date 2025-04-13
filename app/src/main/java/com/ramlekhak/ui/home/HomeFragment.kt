package com.ramlekhak.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.ramlekhak.R
import com.ramlekhak.databinding.FragmentHomeBinding
import com.ramlekhak.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    
    private val viewModel: HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupObservers()
        setupClickListeners()
    }

    private fun setupObservers() {
        // Update streak count
        viewModel.streakCount.observe(viewLifecycleOwner) { count ->
            binding.streakCount.text = "$count Days"
        }

        // Update today's malas
        viewModel.todayMalas.observe(viewLifecycleOwner) { count ->
            binding.todayMalas.text = count.toString()
        }

        // Update today's count
        viewModel.todayCount.observe(viewLifecycleOwner) { count ->
            binding.todayCount.text = count.toString()
        }

        // Update daily progress
        viewModel.dailyProgress.observe(viewLifecycleOwner) { progress ->
            binding.dailyProgress.progress = progress
        }
    }

    private fun setupClickListeners() {
        // Navigate to writing screen when "Start RAM naam writing" button is clicked
        binding.startWritingButton.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_writingFragment)
        }

        // Share progress
        binding.shareProgressButton.setOnClickListener {
            viewModel.shareProgress()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
