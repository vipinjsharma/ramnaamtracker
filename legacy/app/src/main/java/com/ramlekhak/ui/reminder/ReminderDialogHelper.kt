package com.ramlekhak.ui.reminder

import android.app.Dialog
import android.app.TimePickerDialog
import android.content.Context
import android.view.View
import android.view.Window
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.SwitchCompat
import androidx.cardview.widget.CardView
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale
import com.ramlekhak.R

class ReminderDialogHelper(private val context: Context) {
    
    private var dialog: Dialog? = null
    private lateinit var enableReminderSwitch: SwitchCompat
    private lateinit var timeInput: EditText
    private lateinit var clockIcon: ImageView
    private lateinit var morningButton: Button
    private lateinit var noonButton: Button
    private lateinit var eveningButton: Button
    private lateinit var weekdaysButton: Button
    private lateinit var weekendsButton: Button
    private lateinit var everydayButton: Button
    private lateinit var daySelectorViews: List<TextView>
    private lateinit var saveButton: Button
    private lateinit var closeButton: ImageView
    
    private val calendar = Calendar.getInstance()
    private val timeFormatter = SimpleDateFormat("hh:mm a", Locale.getDefault())
    
    // Reminder state
    private var isReminderEnabled = false
    private var selectedTime: Date? = null
    private var selectedDays: MutableSet<Int> = mutableSetOf() // Calendar.SUNDAY, etc.
    private var selectedDayOption: DayOption = DayOption.NONE
    
    private var onSaveSettingsListener: ((ReminderSettings) -> Unit)? = null
    
    enum class DayOption {
        NONE, WEEKDAYS, WEEKENDS, EVERYDAY
    }
    
    data class ReminderSettings(
        val isEnabled: Boolean,
        val time: Date?,
        val days: Set<Int>
    )
    
    fun setOnSaveSettingsListener(listener: (ReminderSettings) -> Unit) {
        onSaveSettingsListener = listener
    }
    
    fun showDialog() {
        if (dialog == null) {
            initDialog()
        }
        
        updateUI()
        dialog?.show()
    }
    
    fun setInitialSettings(settings: ReminderSettings) {
        isReminderEnabled = settings.isEnabled
        selectedTime = settings.time
        selectedDays = settings.days.toMutableSet()
        
        // Determine day option based on days
        selectedDayOption = when {
            selectedDays.isEmpty() -> DayOption.NONE
            selectedDays.containsAll(WEEKDAYS) && selectedDays.size == 5 -> DayOption.WEEKDAYS
            selectedDays.containsAll(WEEKENDS) && selectedDays.size == 2 -> DayOption.WEEKENDS
            selectedDays.size == 7 -> DayOption.EVERYDAY
            else -> DayOption.NONE
        }
    }
    
    private fun initDialog() {
        dialog = Dialog(context).apply {
            requestWindowFeature(Window.FEATURE_NO_TITLE)
            setContentView(R.layout.dialog_daily_reminder)
            setCancelable(true)
            
            // Initialize views
            enableReminderSwitch = findViewById(R.id.reminderSwitch)
            timeInput = findViewById(R.id.timeInput)
            clockIcon = findViewById(R.id.clockIcon)
            morningButton = findViewById(R.id.morningButton)
            noonButton = findViewById(R.id.noonButton)
            eveningButton = findViewById(R.id.eveningButton)
            weekdaysButton = findViewById(R.id.weekdaysButton)
            weekendsButton = findViewById(R.id.weekendsButton)
            everydayButton = findViewById(R.id.everydayButton)
            saveButton = findViewById(R.id.saveButton)
            closeButton = findViewById(R.id.closeButton)
            
            // Initialize day selectors
            daySelectorViews = listOf(
                findViewById(R.id.sundaySelector),
                findViewById(R.id.mondaySelector),
                findViewById(R.id.tuesdaySelector),
                findViewById(R.id.wednesdaySelector),
                findViewById(R.id.thursdaySelector),
                findViewById(R.id.fridaySelector),
                findViewById(R.id.saturdaySelector)
            )
            
            setupListeners()
        }
    }
    
    private fun setupListeners() {
        // Toggle switch listener
        enableReminderSwitch.setOnCheckedChangeListener { _, isChecked ->
            isReminderEnabled = isChecked
            updateUI()
        }
        
        // Time input and clock icon
        val timeClickListener = View.OnClickListener {
            showTimePicker()
        }
        timeInput.setOnClickListener(timeClickListener)
        clockIcon.setOnClickListener(timeClickListener)
        
        // Preset time buttons
        morningButton.setOnClickListener { selectPresetTime(MORNING_HOUR, MORNING_MINUTE, morningButton) }
        noonButton.setOnClickListener { selectPresetTime(NOON_HOUR, NOON_MINUTE, noonButton) }
        eveningButton.setOnClickListener { selectPresetTime(EVENING_HOUR, EVENING_MINUTE, eveningButton) }
        
        // Day option buttons
        weekdaysButton.setOnClickListener { selectDayOption(DayOption.WEEKDAYS, weekdaysButton) }
        weekendsButton.setOnClickListener { selectDayOption(DayOption.WEEKENDS, weekendsButton) }
        everydayButton.setOnClickListener { selectDayOption(DayOption.EVERYDAY, everydayButton) }
        
        // Individual day selectors
        daySelectorViews.forEachIndexed { index, textView ->
            textView.setOnClickListener {
                toggleDaySelection(index + 1) // Calendar days start at 1
                updateDaySelectionUI()
            }
        }
        
        // Save button
        saveButton.setOnClickListener {
            saveSettings()
            dialog?.dismiss()
        }
        
        // Close button
        closeButton.setOnClickListener {
            dialog?.dismiss()
        }
    }
    
    private fun showTimePicker() {
        val currentHour = calendar.get(Calendar.HOUR_OF_DAY)
        val currentMinute = calendar.get(Calendar.MINUTE)
        
        TimePickerDialog(context, { _, hourOfDay, minute ->
            calendar.set(Calendar.HOUR_OF_DAY, hourOfDay)
            calendar.set(Calendar.MINUTE, minute)
            selectedTime = calendar.time
            
            // Update time input field
            timeInput.setText(timeFormatter.format(selectedTime))
            
            // Deselect preset buttons
            deselectAllPresetButtons()
        }, currentHour, currentMinute, false).show()
    }
    
    private fun selectPresetTime(hour: Int, minute: Int, selectedButton: Button) {
        calendar.set(Calendar.HOUR_OF_DAY, hour)
        calendar.set(Calendar.MINUTE, minute)
        selectedTime = calendar.time
        
        // Update time input field
        timeInput.setText(timeFormatter.format(selectedTime))
        
        // Update button selection
        deselectAllPresetButtons()
        selectedButton.isSelected = true
    }
    
    private fun deselectAllPresetButtons() {
        morningButton.isSelected = false
        noonButton.isSelected = false
        eveningButton.isSelected = false
    }
    
    private fun selectDayOption(option: DayOption, selectedButton: Button) {
        selectedDayOption = option
        
        // Update button selection
        weekdaysButton.isSelected = false
        weekendsButton.isSelected = false
        everydayButton.isSelected = false
        selectedButton.isSelected = true
        
        // Update selected days based on option
        selectedDays.clear()
        when (option) {
            DayOption.WEEKDAYS -> selectedDays.addAll(WEEKDAYS)
            DayOption.WEEKENDS -> selectedDays.addAll(WEEKENDS)
            DayOption.EVERYDAY -> selectedDays.addAll(WEEKDAYS + WEEKENDS)
            DayOption.NONE -> { /* No days selected */ }
        }
        
        updateDaySelectionUI()
    }
    
    private fun toggleDaySelection(day: Int) {
        // Calendar days: 1 = Sunday, 2 = Monday, ..., 7 = Saturday
        if (selectedDays.contains(day)) {
            selectedDays.remove(day)
        } else {
            selectedDays.add(day)
        }
        
        // Update day option based on selected days
        selectedDayOption = when {
            selectedDays.isEmpty() -> DayOption.NONE
            selectedDays.containsAll(WEEKDAYS) && selectedDays.size == 5 -> DayOption.WEEKDAYS
            selectedDays.containsAll(WEEKENDS) && selectedDays.size == 2 -> DayOption.WEEKENDS
            selectedDays.size == 7 -> DayOption.EVERYDAY
            else -> DayOption.NONE
        }
        
        // Update day option buttons
        weekdaysButton.isSelected = selectedDayOption == DayOption.WEEKDAYS
        weekendsButton.isSelected = selectedDayOption == DayOption.WEEKENDS
        everydayButton.isSelected = selectedDayOption == DayOption.EVERYDAY
    }
    
    private fun updateDaySelectionUI() {
        // Update individual day selectors
        daySelectorViews.forEachIndexed { index, textView ->
            val day = index + 1 // Calendar days start at 1
            textView.isSelected = selectedDays.contains(day)
        }
    }
    
    private fun saveSettings() {
        val settings = ReminderSettings(
            isEnabled = isReminderEnabled,
            time = selectedTime,
            days = selectedDays.toSet()
        )
        
        onSaveSettingsListener?.invoke(settings)
    }
    
    private fun updateUI() {
        // Enable/disable based on reminder toggle
        val shouldEnable = isReminderEnabled
        timeInput.isEnabled = shouldEnable
        clockIcon.isEnabled = shouldEnable
        morningButton.isEnabled = shouldEnable
        noonButton.isEnabled = shouldEnable
        eveningButton.isEnabled = shouldEnable
        weekdaysButton.isEnabled = shouldEnable
        weekendsButton.isEnabled = shouldEnable
        everydayButton.isEnabled = shouldEnable
        daySelectorViews.forEach { it.isEnabled = shouldEnable }
        
        // Update toggle state
        enableReminderSwitch.isChecked = isReminderEnabled
        
        // Update time display
        selectedTime?.let {
            timeInput.setText(timeFormatter.format(it))
        }
        
        // Update preset time buttons
        updatePresetTimeButtons()
        
        // Update day option buttons
        weekdaysButton.isSelected = selectedDayOption == DayOption.WEEKDAYS
        weekendsButton.isSelected = selectedDayOption == DayOption.WEEKENDS
        everydayButton.isSelected = selectedDayOption == DayOption.EVERYDAY
        
        // Update day selectors
        updateDaySelectionUI()
    }
    
    private fun updatePresetTimeButtons() {
        deselectAllPresetButtons()
        
        selectedTime?.let {
            val hour = calendar.apply { time = it }.get(Calendar.HOUR_OF_DAY)
            val minute = calendar.apply { time = it }.get(Calendar.MINUTE)
            
            when {
                hour == MORNING_HOUR && minute == MORNING_MINUTE -> morningButton.isSelected = true
                hour == NOON_HOUR && minute == NOON_MINUTE -> noonButton.isSelected = true
                hour == EVENING_HOUR && minute == EVENING_MINUTE -> eveningButton.isSelected = true
            }
        }
    }
    
    fun dismiss() {
        dialog?.dismiss()
        dialog = null
    }
    
    companion object {
        // Preset times
        const val MORNING_HOUR = 7
        const val MORNING_MINUTE = 0
        const val NOON_HOUR = 12
        const val NOON_MINUTE = 0
        const val EVENING_HOUR = 18
        const val EVENING_MINUTE = 0
        
        // Day sets
        val WEEKDAYS = setOf(Calendar.MONDAY, Calendar.TUESDAY, Calendar.WEDNESDAY, Calendar.THURSDAY, Calendar.FRIDAY)
        val WEEKENDS = setOf(Calendar.SATURDAY, Calendar.SUNDAY)
    }
} 