package com.ramlekhak.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.android.material.button.MaterialButton
import com.ramlekhak.R
import com.ramlekhak.viewmodel.HomeViewModel

class HomeFragment : Fragment() {

    private lateinit var viewModel: HomeViewModel
    
    // UI elements
    private lateinit var todayCountText: TextView
    private lateinit var totalCountText: TextView
    private lateinit var malaCountText: TextView
    private lateinit var startWritingButton: MaterialButton
    private lateinit var viewAllBenefitsText: TextView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        // Initialize ViewModel
        viewModel = ViewModelProvider(this)[HomeViewModel::class.java]
        
        // Initialize UI elements
        todayCountText = view.findViewById(R.id.today_count)
        totalCountText = view.findViewById(R.id.total_count)
        malaCountText = view.findViewById(R.id.mala_count)
        startWritingButton = view.findViewById(R.id.start_writing_button)
        viewAllBenefitsText = view.findViewById(R.id.view_all_benefits)
        
        // Set up observers
        setupObservers()
        
        // Set up click listeners
        setupClickListeners()
    }

    private fun setupObservers() {
        viewModel.todayCount.observe(viewLifecycleOwner) { count ->
            todayCountText.text = getString(R.string.today_count, count ?: 0)
        }
        
        viewModel.totalCount.observe(viewLifecycleOwner) { count ->
            totalCountText.text = getString(R.string.total_count, count ?: 0)
        }
        
        viewModel.malaCount.observe(viewLifecycleOwner) { count ->
            malaCountText.text = getString(R.string.mala_count, count)
        }
    }

    private fun setupClickListeners() {
        startWritingButton.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_writingFragment)
        }
        
        viewAllBenefitsText.setOnClickListener {
            findNavController().navigate(R.id.benefitsFragment)
        }
    }
}
