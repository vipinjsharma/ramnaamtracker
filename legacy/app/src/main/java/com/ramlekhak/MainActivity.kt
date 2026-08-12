package com.ramlekhak

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.google.android.material.navigation.NavigationView
import com.ramlekhak.data.CountRepository
import com.ramlekhak.ui.dialogs.DialogManager
import com.ramlekhak.ui.rating.RatingActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navController: NavController
    
    @Inject
    lateinit var countRepository: CountRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))
        
        // Hide the default ActionBar title
        supportActionBar?.setDisplayShowTitleEnabled(false)

        drawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        
        // Set up the NavController properly
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController
        
        // Set up the menu button to open the drawer
        findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbar).apply {
            findViewById<android.widget.ImageButton>(R.id.menu_button).setOnClickListener {
                drawerLayout.openDrawer(GravityCompat.START)
            }
            
            // Add click listener for profile button
            findViewById<android.widget.ImageButton>(R.id.profile_button).setOnClickListener {
                navController.navigate(R.id.profileFragment)
            }
        }
        
        // Set up drawer close button
        val headerView = navView.getHeaderView(0)
        headerView.findViewById<android.widget.ImageButton>(R.id.drawer_close_button)?.setOnClickListener {
            drawerLayout.closeDrawer(GravityCompat.START)
        }
        
        // Update user stats in nav header
        updateNavHeaderUserStats(headerView)
        
        // Set navigation item selected listener for custom handling
        navView.setNavigationItemSelectedListener(this)
        
        setupNavigation()
        checkIfShouldShowRatingPrompt()
    }
    
    private fun updateNavHeaderUserStats(headerView: View) {
        // Find the views in the header
        val malasCompleted = headerView.findViewById<TextView>(R.id.malas_completed)
        val userName = headerView.findViewById<TextView>(R.id.user_name)
        
        // Get user name from preferences
        lifecycleScope.launch {
            // Get user name from UserPreferences
            val userPrefs = androidx.preference.PreferenceManager.getDefaultSharedPreferences(this@MainActivity)
            val name = userPrefs.getString("user_name", "Ram Bhakt") ?: "Ram Bhakt"
            userName.text = name
            
            // Get malas completed count from repository
            val totalMalas = countRepository.getTotalMalas()
            malasCompleted.text = "$totalMalas malas completed"
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
    
    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Close drawer when item is tapped
        drawerLayout.closeDrawer(GravityCompat.START)

        when (item.itemId) {
            R.id.writingFragment -> {
                navController.navigate(R.id.writingFragment)
            }
            R.id.profileFragment -> {
                navController.navigate(R.id.profileFragment)
            }
            R.id.languageSettingItem -> {
                navController.navigate(R.id.settingsFragment)
            }
            R.id.howToUseItem -> {
                // Show how to use dialog
                DialogManager.showHowToUseDialog(this)
            }
            R.id.nav_feedback -> {
                // Show feedback dialog
                DialogManager.showFeedbackDialog(this)
            }
            R.id.rateAppItem -> {
                val intent = Intent(this, RatingActivity::class.java)
                startActivity(intent)
            }
            R.id.nav_share -> {
                shareApp()
            }
            R.id.termsItem -> {
                // Show terms and conditions dialog
                DialogManager.showTermsAndConditionsDialog(this)
            }
            R.id.privacyItem -> {
                // Show privacy policy dialog
                DialogManager.showPrivacyPolicyDialog(this)
            }
        }
        return true
    }
    
    private fun setupNavigation() {
        // This method is now empty as the navigation setup is done in the onCreate method
    }

    private fun checkIfShouldShowRatingPrompt() {
        if (RatingActivity.shouldShowRatingPrompt(this)) {
            val intent = Intent(this, RatingActivity::class.java)
            startActivity(intent)
        }
    }

    private fun shareApp() {
        lifecycleScope.launch {
            val totalCount = countRepository.getTotalMalas() * 108 // Convert malas to count
            val shareMessage = getString(R.string.share_message, totalCount)
            val intent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT, shareMessage)
                type = "text/plain"
            }
            startActivity(Intent.createChooser(intent, getString(R.string.share_title)))
        }
    }
}
