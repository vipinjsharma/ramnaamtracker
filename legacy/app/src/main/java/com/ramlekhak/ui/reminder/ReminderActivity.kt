package com.ramlekhak.ui.reminder

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.ramlekhak.R
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class ReminderActivity : AppCompatActivity() {

    private lateinit var reminderDialogHelper: ReminderDialogHelper
    private lateinit var showDialogButton: Button
    private lateinit var statusTextView: TextView
    private val dateFormatter = SimpleDateFormat("hh:mm a", Locale.getDefault())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reminder)

        showDialogButton = findViewById(R.id.showDialogButton)
        statusTextView = findViewById(R.id.statusTextView)

        setupReminderDialog()
        setupClickListeners()
    }

    private fun setupReminderDialog() {
        reminderDialogHelper = ReminderDialogHelper(this)
        
        // Set initial values (from preferences or defaults)
        val initialSettings = ReminderDialogHelper.ReminderSettings(
            isEnabled = false,
            time = Calendar.getInstance().apply {
                set(Calendar.HOUR_OF_DAY, 9)
                set(Calendar.MINUTE, 0)
            }.time,
            days = setOf(Calendar.MONDAY, Calendar.WEDNESDAY, Calendar.FRIDAY)
        )
        
        reminderDialogHelper.setInitialSettings(initialSettings)
        
        // Set listener for when settings are saved
        reminderDialogHelper.setOnSaveSettingsListener { settings ->
            // In a real app, save these settings to preferences
            updateStatusText(settings)
        }
    }

    private fun setupClickListeners() {
        showDialogButton.setOnClickListener {
            reminderDialogHelper.showDialog()
        }
    }

    private fun updateStatusText(settings: ReminderDialogHelper.ReminderSettings) {
        val statusBuilder = StringBuilder()
        
        statusBuilder.append("Reminder Status: ${if (settings.isEnabled) "Enabled" else "Disabled"}\n\n")
        
        settings.time?.let {
            statusBuilder.append("Time: ${dateFormatter.format(it)}\n\n")
        }
        
        statusBuilder.append("Days: ")
        if (settings.days.isEmpty()) {
            statusBuilder.append("None")
        } else {
            val dayNames = settings.days.map { getDayName(it) }
            statusBuilder.append(dayNames.joinToString(", "))
        }
        
        statusTextView.text = statusBuilder.toString()
    }
    
    private fun getDayName(day: Int): String {
        return when (day) {
            Calendar.SUNDAY -> "Sunday"
            Calendar.MONDAY -> "Monday"
            Calendar.TUESDAY -> "Tuesday"
            Calendar.WEDNESDAY -> "Wednesday"
            Calendar.THURSDAY -> "Thursday"
            Calendar.FRIDAY -> "Friday"
            Calendar.SATURDAY -> "Saturday"
            else -> "Unknown"
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        reminderDialogHelper.dismiss()
    }
} 