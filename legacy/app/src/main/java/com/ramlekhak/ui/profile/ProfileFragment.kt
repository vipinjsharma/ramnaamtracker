package com.ramlekhak.ui.profile

import android.app.Dialog
import android.app.TimePickerDialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.Switch
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.google.android.material.card.MaterialCardView
import com.ramlekhak.R
import com.ramlekhak.databinding.FragmentProfileBinding
import dagger.hilt.android.AndroidEntryPoint
import java.util.Calendar
import java.text.SimpleDateFormat
import java.util.Locale

@AndroidEntryPoint
class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!
    private val viewModel: ProfileViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        setupProfileName()
        setupObservers()
        setupClickListeners()
    }

    private fun setupProfileName() {
        // Get current name from shared preferences or default
        val currentName = viewModel.getUserName()
        binding.profileName.text = currentName
        
        // First two letters for avatar
        val initials = currentName.split(" ")
            .mapNotNull { it.firstOrNull()?.toString() }
            .take(2)
            .joinToString("")
        binding.profileAvatar.text = initials
    }

    private fun setupObservers() {
        viewModel.totalCount.observe(viewLifecycleOwner, Observer {
            binding.totalCount.text = it.toString()
        })
        
        viewModel.totalMalas.observe(viewLifecycleOwner, Observer {
            binding.totalMalas.text = it.toString()
        })
        
        viewModel.currentStreak.observe(viewLifecycleOwner, Observer {
            binding.currentStreak.text = "$it days"
        })
        
        viewModel.longestStreak.observe(viewLifecycleOwner, Observer {
            binding.longestStreak.text = "$it days"
        })
        
        viewModel.dailyGoal.observe(viewLifecycleOwner, Observer { goal ->
            viewModel.todayCount.observe(viewLifecycleOwner, Observer { count ->
                val percentage = if (goal > 0) (count.toFloat() / goal.toFloat() * 100).toInt() else 0
                binding.dailyGoalText.text = "$count/$goal ($percentage%)"
                binding.dailyGoalProgress.progress = percentage
            })
        })
        
        viewModel.monthlyGoal.observe(viewLifecycleOwner, Observer { goal ->
            viewModel.monthCount.observe(viewLifecycleOwner, Observer { count ->
                val malasCompleted = count / 108
                val totalMalas = goal / 108
                binding.monthlyGoalText.text = "$count/$goal ($malasCompleted of $totalMalas malas completed)"
                
                val percentage = if (goal > 0) (count.toFloat() / goal.toFloat() * 100).toInt() else 0
                binding.monthlyGoalProgress.progress = percentage
            })
        })
    }

    private fun setupClickListeners() {
        // Handle profile name edit
        binding.profileName.setOnClickListener {
            showNameEditDialog()
        }
        
        // Make the pencil icon clickable too
        binding.editProfileIcon.setOnClickListener {
            showNameEditDialog()
        }
        
        // Handle edit goals button
        binding.editGoalsButton.setOnClickListener {
            showGoalsDialog()
        }
        
        // Handle reminder settings button
        binding.reminderSettingsButton.setOnClickListener {
            showReminderDialog()
        }
        
        // Handle logout button
        binding.logoutButton.setOnClickListener {
            // Handle logout logic
            Toast.makeText(context, "Logout clicked", Toast.LENGTH_SHORT).show()
        }
    }
    
    private fun showNameEditDialog() {
        val dialog = Dialog(requireContext())
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.dialog_edit_name)
        
        val editText = dialog.findViewById<EditText>(R.id.name_edit_text)
        val confirmButton = dialog.findViewById<Button>(R.id.confirm_button)
        val cancelButton = dialog.findViewById<Button>(R.id.cancel_button)
        
        // Set current name in edit text
        editText.setText(binding.profileName.text)
        
        confirmButton.setOnClickListener {
            val newName = editText.text.toString().trim()
            if (newName.isNotEmpty()) {
                binding.profileName.text = newName
                viewModel.saveUserName(newName)
                
                // Update initials
                val initials = newName.split(" ")
                    .mapNotNull { it.firstOrNull()?.toString() }
                    .take(2)
                    .joinToString("")
                binding.profileAvatar.text = initials
                
                dialog.dismiss()
            } else {
                Toast.makeText(context, "Name cannot be empty", Toast.LENGTH_SHORT).show()
            }
        }
        
        cancelButton.setOnClickListener {
            dialog.dismiss()
        }
        
        dialog.show()
    }
    
    private fun showGoalsDialog() {
        val dialog = Dialog(requireContext())
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.dialog_set_goals)
        
        val dailyGoalInput = dialog.findViewById<EditText>(R.id.daily_goal_input)
        val mala1Button = dialog.findViewById<Button>(R.id.btn_mala_1)
        val mala2Button = dialog.findViewById<Button>(R.id.btn_mala_2)
        val mala10Button = dialog.findViewById<Button>(R.id.btn_mala_10)
        
        val monthlyGoalInput = dialog.findViewById<EditText>(R.id.monthly_goal_input)
        val mala30Button = dialog.findViewById<Button>(R.id.btn_mala_30)
        val mala100Button = dialog.findViewById<Button>(R.id.btn_mala_100)
        val mala200Button = dialog.findViewById<Button>(R.id.btn_mala_200)
        
        val saveButton = dialog.findViewById<Button>(R.id.save_goals_button)
        
        // Set current goals
        dailyGoalInput.setText(viewModel.dailyGoal.value?.toString() ?: "108")
        monthlyGoalInput.setText(viewModel.monthlyGoal.value?.toString() ?: "3240")
        
        // Quick selection buttons for daily goal
        mala1Button.setOnClickListener { dailyGoalInput.setText("108") }
        mala2Button.setOnClickListener { dailyGoalInput.setText("216") }
        mala10Button.setOnClickListener { dailyGoalInput.setText("1080") }
        
        // Quick selection buttons for monthly goal
        mala30Button.setOnClickListener { monthlyGoalInput.setText("3240") }
        mala100Button.setOnClickListener { monthlyGoalInput.setText("10800") }
        mala200Button.setOnClickListener { monthlyGoalInput.setText("21600") }
        
        saveButton.setOnClickListener {
            try {
                val dailyGoal = dailyGoalInput.text.toString().toInt()
                val monthlyGoal = monthlyGoalInput.text.toString().toInt()
                
                viewModel.saveGoals(dailyGoal, monthlyGoal)
                dialog.dismiss()
            } catch (e: NumberFormatException) {
                Toast.makeText(context, "Please enter valid numbers", Toast.LENGTH_SHORT).show()
            }
        }
        
        dialog.show()
    }
    
    private fun showReminderDialog() {
        val dialog = Dialog(requireContext())
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.dialog_reminder_settings)
        
        val enableSwitch = dialog.findViewById<Switch>(R.id.enable_reminder_switch)
        val timeInput = dialog.findViewById<EditText>(R.id.reminder_time_input)
        val morningButton = dialog.findViewById<Button>(R.id.btn_morning)
        val noonButton = dialog.findViewById<Button>(R.id.btn_noon)
        val eveningButton = dialog.findViewById<Button>(R.id.btn_evening)
        
        val weekdaysButton = dialog.findViewById<Button>(R.id.btn_weekdays)
        val weekendsButton = dialog.findViewById<Button>(R.id.btn_weekends)
        val everydayButton = dialog.findViewById<Button>(R.id.btn_everyday)
        
        val dayButtons = listOf(
            dialog.findViewById<Button>(R.id.btn_sunday),
            dialog.findViewById<Button>(R.id.btn_monday),
            dialog.findViewById<Button>(R.id.btn_tuesday),
            dialog.findViewById<Button>(R.id.btn_wednesday),
            dialog.findViewById<Button>(R.id.btn_thursday),
            dialog.findViewById<Button>(R.id.btn_friday),
            dialog.findViewById<Button>(R.id.btn_saturday)
        )
        
        val saveButton = dialog.findViewById<Button>(R.id.save_settings_button)
        
        // Set current reminder settings
        val reminderSettings = viewModel.getReminderSettings()
        enableSwitch.isChecked = reminderSettings.enabled
        timeInput.setText(reminderSettings.time)
        
        // Set selected days
        for (i in dayButtons.indices) {
            if (reminderSettings.days.contains(i)) {
                dayButtons[i].setBackgroundResource(R.drawable.circle_selected)
            } else {
                dayButtons[i].setBackgroundResource(R.drawable.circle_unselected)
            }
        }
        
        // Quick selection buttons for time
        morningButton.setOnClickListener { timeInput.setText("7:00 AM") }
        noonButton.setOnClickListener { timeInput.setText("12:00 PM") }
        eveningButton.setOnClickListener { timeInput.setText("6:00 PM") }
        
        // Quick selection buttons for days
        weekdaysButton.setOnClickListener {
            for (i in dayButtons.indices) {
                if (i > 0 && i < 6) { // Monday to Friday
                    dayButtons[i].setBackgroundResource(R.drawable.circle_selected)
                } else {
                    dayButtons[i].setBackgroundResource(R.drawable.circle_unselected)
                }
            }
        }
        
        weekendsButton.setOnClickListener {
            for (i in dayButtons.indices) {
                if (i == 0 || i == 6) { // Saturday and Sunday
                    dayButtons[i].setBackgroundResource(R.drawable.circle_selected)
                } else {
                    dayButtons[i].setBackgroundResource(R.drawable.circle_unselected)
                }
            }
        }
        
        everydayButton.setOnClickListener {
            for (button in dayButtons) {
                button.setBackgroundResource(R.drawable.circle_selected)
            }
        }
        
        // Day selection
        for (i in dayButtons.indices) {
            dayButtons[i].setOnClickListener {
                if (dayButtons[i].background.constantState == 
                    resources.getDrawable(R.drawable.circle_selected).constantState) {
                    dayButtons[i].setBackgroundResource(R.drawable.circle_unselected)
                } else {
                    dayButtons[i].setBackgroundResource(R.drawable.circle_selected)
                }
            }
        }
        
        saveButton.setOnClickListener {
            val selectedDays = mutableListOf<Int>()
            for (i in dayButtons.indices) {
                if (dayButtons[i].background.constantState == 
                    resources.getDrawable(R.drawable.circle_selected).constantState) {
                    selectedDays.add(i)
                }
            }
            
            val settings = ReminderSettings(
                enabled = enableSwitch.isChecked,
                time = timeInput.text.toString(),
                days = selectedDays
            )
            
            viewModel.saveReminderSettings(settings)
            Toast.makeText(context, "Reminder settings saved", Toast.LENGTH_SHORT).show()
            dialog.dismiss()
        }
        
        dialog.show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
} 