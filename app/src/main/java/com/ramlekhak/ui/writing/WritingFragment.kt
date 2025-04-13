package com.ramlekhak.ui.writing

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.ramlekhak.R
import com.ramlekhak.databinding.FragmentWritingBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WritingFragment : Fragment() {

    private var _binding: FragmentWritingBinding? = null
    private val binding get() = _binding!!

    private val viewModel: WritingViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWritingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        setupObservers()
        setupClickListeners()
    }

    private fun setupObservers() {
        // Observe today's count
        viewModel.todayCount.observe(viewLifecycleOwner) { count ->
            binding.todayCountValue.text = count.toString()
        }
        
        // Observe streak count
        viewModel.streakCount.observe(viewLifecycleOwner) { count ->
            binding.streakCountValue.text = "$count Days"
        }
        
        // Observe today's malas
        viewModel.todayMalas.observe(viewLifecycleOwner) { malas ->
            binding.todayMalasValue.text = malas.toString()
        }
        
        // Observe daily progress
        viewModel.dailyProgress.observe(viewLifecycleOwner) { progress ->
            binding.dailyProgressBar.progress = progress
            binding.goalPercentage.text = "$progress%"
        }
    }

    private fun setupClickListeners() {
        // Clear drawing button
        binding.clearButton.setOnClickListener {
            binding.drawingView.clear()
        }
        
        // Auto-draw RAM button
        binding.autoDrawButton.setOnClickListener {
            val ramBitmap = viewModel.generateRamDrawing()
            binding.drawingView.setBitmap(ramBitmap)
        }
        
        // Submit button
        binding.submitButton.setOnClickListener {
            val drawingBitmap = binding.drawingView.getBitmap()
            viewModel.submitDrawing(drawingBitmap)
            binding.drawingView.clear()
        }
        
        // Share progress button
        binding.shareProgressButton.setOnClickListener {
            shareProgress()
        }
    }
    
    private fun shareProgress() {
        val streakText = binding.streakCountValue.text
        val malasText = binding.todayMalasValue.text
        val countText = binding.todayCountValue.text
        
        val shareText = "I've been writing RAM naam for $streakText with $malasText malas and a total count of $countText today! Join me in this spiritual journey. #RAMNaamJap"
        
        val shareIntent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, shareText)
            type = "text/plain"
        }
        
        startActivity(Intent.createChooser(shareIntent, "Share your progress"))
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
