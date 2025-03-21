package com.ramlekhak.ui.settings

import android.content.Context
import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import com.google.android.material.switchmaterial.SwitchMaterial
import com.ramlekhak.R
import com.ramlekhak.data.UserPreferences
import java.util.Locale

class SettingsFragment : Fragment() {

    private lateinit var userPreferences: UserPreferences
    
    // UI elements
    private lateinit var languageRadioGroup: RadioGroup
    private lateinit var radioEnglish: RadioButton
    private lateinit var radioHindi: RadioButton
    private lateinit var themeSwitch: SwitchMaterial
    private lateinit var notificationSwitch: SwitchMaterial
    private lateinit var resetMidnightSwitch: SwitchMaterial

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_settings, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        // Initialize preferences
        userPreferences = UserPreferences(requireContext())
        
        // Initialize UI elements
        languageRadioGroup = view.findViewById(R.id.language_radio_group)
        radioEnglish = view.findViewById(R.id.radio_english)
        radioHindi = view.findViewById(R.id.radio_hindi)
        themeSwitch = view.findViewById(R.id.theme_switch)
        notificationSwitch = view.findViewById(R.id.notification_switch)
        resetMidnightSwitch = view.findViewById(R.id.reset_midnight_switch)
        
        // Load current settings
        loadCurrentSettings()
        
        // Setup listeners
        setupListeners()
    }

    private fun loadCurrentSettings() {
        // Language
        val currentLanguage = userPreferences.getLanguage()
        if (currentLanguage == "hi") {
            radioHindi.isChecked = true
        } else {
            radioEnglish.isChecked = true
        }
        
        // Theme
        themeSwitch.isChecked = userPreferences.isDarkThemeEnabled()
        
        // Notifications
        notificationSwitch.isChecked = userPreferences.areNotificationsEnabled()
        
        // Reset at midnight
        resetMidnightSwitch.isChecked = userPreferences.isResetAtMidnightEnabled()
    }

    private fun setupListeners() {
        // Language change listener
        languageRadioGroup.setOnCheckedChangeListener { _, checkedId ->
            val lang = when (checkedId) {
                R.id.radio_hindi -> "hi"
                else -> "en"
            }
            
            if (lang != userPreferences.getLanguage()) {
                userPreferences.setLanguage(lang)
                updateLocale(lang)
                requireActivity().recreate()
            }
        }
        
        // Theme switch listener
        themeSwitch.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked != userPreferences.isDarkThemeEnabled()) {
                userPreferences.setDarkThemeEnabled(isChecked)
            }
        }
        
        // Notifications switch listener
        notificationSwitch.setOnCheckedChangeListener { _, isChecked ->
            userPreferences.setNotificationsEnabled(isChecked)
        }
        
        // Reset at midnight switch listener
        resetMidnightSwitch.setOnCheckedChangeListener { _, isChecked ->
            userPreferences.setResetAtMidnightEnabled(isChecked)
        }
    }

    private fun updateLocale(languageCode: String) {
        val locale = Locale(languageCode)
        Locale.setDefault(locale)
        val config = Configuration()
        config.locale = locale
        requireContext().resources.updateConfiguration(
            config,
            requireContext().resources.displayMetrics
        )
    }
}
