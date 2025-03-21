package com.ramlekhak.ui.writing

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.button.MaterialButton
import com.ramlekhak.R
import com.ramlekhak.utils.DrawingView

class WritingFragment : Fragment() {

    private lateinit var viewModel: WritingViewModel
    
    // UI elements
    private lateinit var drawingView: DrawingView
    private lateinit var clearButton: MaterialButton
    private lateinit var autoDrawButton: MaterialButton
    private lateinit var submitButton: MaterialButton
    private lateinit var currentCountText: TextView
    private lateinit var todayCountText: TextView
    private lateinit var totalCountText: TextView
    private lateinit var malaCountText: TextView
    
    // Count tracking
    private var currentCount = 0

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
        
        // Initialize count text views if they exist
        view.findViewById<TextView>(R.id.current_count_text)?.let { currentCountText = it }
        view.findViewById<TextView>(R.id.today_count_text)?.let { todayCountText = it }
        view.findViewById<TextView>(R.id.total_count_text)?.let { totalCountText = it }
        view.findViewById<TextView>(R.id.mala_count_text)?.let { malaCountText = it }
        
        // Set up touch detection for drawing
        setupDrawingTouchDetection()
        
        // Set up observers
        setupObservers()
        
        // Set up click listeners
        setupClickListeners()
    }

    private fun setupDrawingTouchDetection() {
        // We'll have the drawing view notify us when writing is complete
        drawingView.setOnDrawingCompleteListener {
            viewModel.incrementCount()
        }
    }

    private fun setupObservers() {
        // Observe current count
        viewModel.currentCount.observe(viewLifecycleOwner) { count ->
            if (::currentCountText.isInitialized) {
                currentCountText.text = getString(R.string.current_count, count)
            }
            currentCount = count
        }
        
        // Observe today's count
        viewModel.todayCount.observe(viewLifecycleOwner) { count ->
            if (::todayCountText.isInitialized) {
                todayCountText.text = getString(R.string.today_count, count ?: 0)
            }
        }
        
        // Observe total count
        viewModel.totalCount.observe(viewLifecycleOwner) { count ->
            if (::totalCountText.isInitialized) {
                totalCountText.text = getString(R.string.total_count, count ?: 0)
            }
        }
        
        // Observe mala count
        viewModel.currentMalaCount.observe(viewLifecycleOwner) { count ->
            if (::malaCountText.isInitialized) {
                malaCountText.text = getString(R.string.mala_count, count)
            }
        }
    }

    private fun setupClickListeners() {
        clearButton.setOnClickListener {
            drawingView.clearCanvas()
            viewModel.resetCurrentCount()
        }
        
        autoDrawButton.setOnClickListener {
            drawingView.autoDraw()
            // Auto-draw counts as one writing
            viewModel.incrementCount()
        }
        
        submitButton.setOnClickListener {
            if (currentCount > 0) {
                viewModel.submitCount()
                Toast.makeText(
                    requireContext(),
                    getString(R.string.count_submitted_format, currentCount),
                    Toast.LENGTH_SHORT
                ).show()
                drawingView.clearCanvas()
            } else {
                Toast.makeText(
                    requireContext(),
                    getString(R.string.no_count_to_submit),
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
}
