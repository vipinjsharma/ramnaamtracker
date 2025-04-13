package com.ramlekhak.ui.statistics

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.ramlekhak.databinding.FragmentStatisticsBinding
import com.ramlekhak.viewmodel.StatisticsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class StatisticsFragment : Fragment() {

    private var _binding: FragmentStatisticsBinding? = null
    private val binding get() = _binding!!

    private val viewModel: StatisticsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentStatisticsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupObservers()
        setupClickListeners()
    }

    private fun setupObservers() {
        viewModel.totalMalas.observe(viewLifecycleOwner) { malas ->
            binding.tvTotalMalas.text = "Total Malas: $malas"
        }

        viewModel.currentStreak.observe(viewLifecycleOwner) { streak ->
            binding.tvCurrentStreak.text = "Current Streak: $streak days"
        }

        viewModel.longestStreak.observe(viewLifecycleOwner) { streak ->
            binding.tvLongestStreak.text = "Longest Streak: $streak days"
        }

        viewModel.monthlyProgress.observe(viewLifecycleOwner) { progress ->
            binding.pbMonthlyProgress.progress = progress
        }

        viewModel.yearlyProgress.observe(viewLifecycleOwner) { progress ->
            binding.pbYearlyProgress.progress = progress
        }
    }

    private fun setupClickListeners() {
        binding.btnResetStats.setOnClickListener {
            viewModel.resetStatistics()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
} 