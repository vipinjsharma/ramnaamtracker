package com.ramlekhak.ui.stats

import android.app.AlertDialog
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.button.MaterialButton
import com.ramlekhak.R
import com.ramlekhak.viewmodel.StatisticsViewModel

class StatisticsFragment : Fragment() {

    private lateinit var viewModel: StatisticsViewModel
    
    // UI elements
    private lateinit var resetStatsButton: MaterialButton

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_statistics, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        // Initialize ViewModel
        viewModel = ViewModelProvider(this)[StatisticsViewModel::class.java]
        
        // Initialize UI elements
        resetStatsButton = view.findViewById(R.id.reset_stats_button)
        
        // Setup observers
        setupObservers()
        
        // Setup click listeners
        setupClickListeners()
        
        // Setup charts
        setupCharts(view)
    }

    private fun setupObservers() {
        viewModel.resetEvent.observe(viewLifecycleOwner) { event ->
            when (event) {
                is StatisticsViewModel.ResetEvent.Success -> {
                    Toast.makeText(
                        requireContext(),
                        "Statistics have been reset",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                is StatisticsViewModel.ResetEvent.Error -> {
                    Toast.makeText(requireContext(), event.message, Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    private fun setupClickListeners() {
        resetStatsButton.setOnClickListener {
            showResetConfirmationDialog()
        }
    }

    private fun showResetConfirmationDialog() {
        AlertDialog.Builder(requireContext())
            .setTitle("Reset Statistics")
            .setMessage(getString(R.string.reset_confirmation))
            .setPositiveButton(getString(R.string.yes)) { _, _ ->
                viewModel.resetStatistics()
            }
            .setNegativeButton(getString(R.string.no), null)
            .show()
    }

    private fun setupCharts(view: View) {
        // In a real implementation, we would use a charting library like MPAndroidChart
        // to visualize the data. For the purposes of this example, we're just setting
        // up placeholders for where the charts would go.
        
        val dailyChart = view.findViewById<View>(R.id.daily_chart)
        val weeklyChart = view.findViewById<View>(R.id.weekly_chart)
        val monthlyChart = view.findViewById<View>(R.id.monthly_chart)
        
        // Observe the data for charts
        viewModel.dailyStats.observe(viewLifecycleOwner) { dailyData ->
            // Update daily chart with data
            // This would use a charting library in a real implementation
            if (dailyData.isEmpty()) {
                dailyChart.setBackgroundColor(Color.LTGRAY)
            } else {
                dailyChart.setBackgroundColor(Color.TRANSPARENT)
                // Here we would update the chart with dailyData
            }
        }
        
        viewModel.monthlyStats.observe(viewLifecycleOwner) { monthlyData ->
            // Update monthly chart with data
            if (monthlyData.isEmpty()) {
                monthlyChart.setBackgroundColor(Color.LTGRAY)
            } else {
                monthlyChart.setBackgroundColor(Color.TRANSPARENT)
                // Here we would update the chart with monthlyData
            }
        }
    }
}
