package com.ramlekhak.ui.writing

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.button.MaterialButton
import com.ramlekhak.R
import com.ramlekhak.utils.DrawingView
import com.ramlekhak.viewmodel.WritingViewModel

class WritingFragment : Fragment() {

    private lateinit var viewModel: WritingViewModel
    
    // UI elements
    private lateinit var drawingView: DrawingView
    private lateinit var clearButton: MaterialButton
    private lateinit var autoDrawButton: MaterialButton
    private lateinit var submitButton: MaterialButton
    
    // Count for auto-draw
    private var isAutoDraw = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_writing, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        // Initialize ViewModel
        viewModel = ViewModelProvider(this)[WritingViewModel::class.java]
        
        // Initialize UI elements
        drawingView = view.findViewById(R.id.drawing_view)
        clearButton = view.findViewById(R.id.clear_button)
        autoDrawButton = view.findViewById(R.id.auto_draw_button)
        submitButton = view.findViewById(R.id.submit_button)
        
        // Set up observers
        setupObservers()
        
        // Set up click listeners
        setupClickListeners()
    }

    private fun setupObservers() {
        viewModel.submissionEvent.observe(viewLifecycleOwner) { event ->
            when (event) {
                is WritingViewModel.SubmissionEvent.Success -> {
                    Toast.makeText(
                        requireContext(),
                        getString(R.string.count_submitted),
                        Toast.LENGTH_SHORT
                    ).show()
                    
                    // Clear the drawing board after submission
                    drawingView.clearCanvas()
                    isAutoDraw = false
                }
                is WritingViewModel.SubmissionEvent.Error -> {
                    Toast.makeText(requireContext(), event.message, Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    private fun setupClickListeners() {
        clearButton.setOnClickListener {
            drawingView.clearCanvas()
            isAutoDraw = false
        }
        
        autoDrawButton.setOnClickListener {
            drawingView.autoDraw()
            isAutoDraw = true
        }
        
        submitButton.setOnClickListener {
            // Submit 1 count for each writing
            viewModel.submitCount(1)
        }
    }
}
