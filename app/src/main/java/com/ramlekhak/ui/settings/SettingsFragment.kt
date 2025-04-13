package com.ramlekhak.ui.settings

import android.content.Context
import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.fragment.app.Fragment
import com.google.android.material.switchmaterial.SwitchMaterial
import com.ramlekhak.R
import com.ramlekhak.data.UserPreferences
import java.util.Locale
import com.ramlekhak.databinding.FragmentSettingsBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SettingsFragment : Fragment() {

    private var _binding: FragmentSettingsBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var userPreferences: UserPreferences
    
    // UI elements
    private lateinit var languageRadioGroup: RadioGroup
    private lateinit var radioEnglish: RadioButton
    private lateinit var radioHindi: RadioButton
    private lateinit var notificationSwitch: SwitchMaterial
    private lateinit var resetMidnightSwitch: SwitchMaterial

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSettingsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        // Initialize UI elements
        languageRadioGroup = binding.languageRadioGroup
        radioEnglish = binding.radioEnglish
        radioHindi = binding.radioHindi
        notificationSwitch = binding.notificationSwitch
        resetMidnightSwitch = binding.resetMidnightSwitch
        
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
        
        // Notifications switch listener
        notificationSwitch.setOnCheckedChangeListener { _, isChecked ->
            userPreferences.setNotificationsEnabled(isChecked)
        }
        
        // Reset at midnight switch listener
        resetMidnightSwitch.setOnCheckedChangeListener { _, isChecked ->
            userPreferences.setResetAtMidnight(isChecked)
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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
