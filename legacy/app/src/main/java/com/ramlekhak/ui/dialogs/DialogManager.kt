package com.ramlekhak.ui.dialogs

import android.app.Dialog
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.Window
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.Toast
import com.ramlekhak.R

/**
 * Utility class to manage and display various dialogs in the app
 */
class DialogManager {
    companion object {
        /**
         * Shows a dialog for sharing progress with different options
         */
        fun showShareProgressDialog(context: Context) {
            val dialog = Dialog(context)
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
            dialog.setContentView(R.layout.dialog_share_progress)
            
            val closeButton = dialog.findViewById<ImageButton>(R.id.btn_close)
            val whatsappButton = dialog.findViewById<Button>(R.id.btn_whatsapp)
            val smsButton = dialog.findViewById<Button>(R.id.btn_sms)
            val emailButton = dialog.findViewById<Button>(R.id.btn_email)
            val copyTextButton = dialog.findViewById<Button>(R.id.btn_copy_text)
            
            val shareText = "I've been writing Ram Naam daily with Ram Lekhak app! " +
                    "My current streak is 5 days, and I've completed 3 malas so far. " +
                    "Join me in this spiritual journey!"
            
            closeButton.setOnClickListener {
                dialog.dismiss()
            }
            
            whatsappButton.setOnClickListener {
                try {
                    val intent = Intent(Intent.ACTION_SEND)
                    intent.type = "text/plain"
                    intent.setPackage("com.whatsapp")
                    intent.putExtra(Intent.EXTRA_TEXT, shareText)
                    context.startActivity(intent)
                } catch (e: Exception) {
                    Toast.makeText(context, "WhatsApp not installed on your device", Toast.LENGTH_SHORT).show()
                }
                dialog.dismiss()
            }
            
            smsButton.setOnClickListener {
                val intent = Intent(Intent.ACTION_SENDTO)
                intent.data = Uri.parse("smsto:")
                intent.putExtra("sms_body", shareText)
                context.startActivity(intent)
                dialog.dismiss()
            }
            
            emailButton.setOnClickListener {
                val intent = Intent(Intent.ACTION_SENDTO)
                intent.data = Uri.parse("mailto:")
                intent.putExtra(Intent.EXTRA_SUBJECT, "My Ram Naam Writing Journey")
                intent.putExtra(Intent.EXTRA_TEXT, shareText)
                context.startActivity(intent)
                dialog.dismiss()
            }
            
            copyTextButton.setOnClickListener {
                val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
                val clip = ClipData.newPlainText("Ram Naam Progress", shareText)
                clipboard.setPrimaryClip(clip)
                Toast.makeText(context, "Text copied to clipboard", Toast.LENGTH_SHORT).show()
                dialog.dismiss()
            }
            
            dialog.show()
        }
        
        /**
         * Shows a dialog for rating the app with stars and feedback
         */
        fun showRateAppDialog(context: Context) {
            val dialog = Dialog(context)
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
            dialog.setContentView(R.layout.dialog_rate_app)
            
            val closeButton = dialog.findViewById<ImageButton>(R.id.btn_close)
            val submitButton = dialog.findViewById<Button>(R.id.btn_submit_rating)
            val feedbackInput = dialog.findViewById<EditText>(R.id.feedback_input)
            
            val stars = listOf<ImageView>(
                dialog.findViewById(R.id.star_1),
                dialog.findViewById(R.id.star_2),
                dialog.findViewById(R.id.star_3),
                dialog.findViewById(R.id.star_4),
                dialog.findViewById(R.id.star_5)
            )
            
            var rating = 0
            
            // Set up star rating system
            stars.forEachIndexed { index, star ->
                star.setOnClickListener {
                    // Update rating
                    rating = index + 1
                    
                    // Update stars display
                    for (i in stars.indices) {
                        if (i <= index) {
                            stars[i].setImageResource(android.R.drawable.btn_star_big_on)
                        } else {
                            stars[i].setImageResource(android.R.drawable.btn_star_big_off)
                        }
                    }
                }
            }
            
            closeButton.setOnClickListener {
                dialog.dismiss()
            }
            
            submitButton.setOnClickListener {
                if (rating > 0) {
                    val feedback = feedbackInput.text.toString()
                    
                    // Handle feedback submission - in a real app this would send to backend
                    Toast.makeText(
                        context, 
                        "Thank you for your ${rating}-star rating!", 
                        Toast.LENGTH_SHORT
                    ).show()
                    
                    // For 4 or 5 star ratings, prompt to rate on Play Store
                    if (rating >= 4) {
                        try {
                            val intent = Intent(Intent.ACTION_VIEW)
                            intent.data = Uri.parse("market://details?id=${context.packageName}")
                            context.startActivity(intent)
                        } catch (e: Exception) {
                            // Play Store app not installed, open in browser
                            val intent = Intent(Intent.ACTION_VIEW)
                            intent.data = Uri.parse("https://play.google.com/store/apps/details?id=${context.packageName}")
                            context.startActivity(intent)
                        }
                    }
                    
                    dialog.dismiss()
                } else {
                    Toast.makeText(context, "Please select a rating", Toast.LENGTH_SHORT).show()
                }
            }
            
            dialog.show()
        }
        
        /**
         * Shows a dialog for submitting general feedback about the app
         */
        fun showFeedbackDialog(context: Context) {
            val dialog = Dialog(context)
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
            dialog.setContentView(R.layout.dialog_leave_feedback)
            
            val closeButton = dialog.findViewById<ImageButton>(R.id.btn_close)
            val submitButton = dialog.findViewById<Button>(R.id.btn_submit_feedback)
            val feedbackInput = dialog.findViewById<EditText>(R.id.feedback_input)
            val emailInput = dialog.findViewById<EditText>(R.id.email_input)
            
            closeButton.setOnClickListener {
                dialog.dismiss()
            }
            
            submitButton.setOnClickListener {
                val feedback = feedbackInput.text.toString()
                val email = emailInput.text.toString()
                
                if (feedback.isEmpty()) {
                    Toast.makeText(context, "Please enter your feedback", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }
                
                // Here you would send the feedback to your backend
                // For now, just show a confirmation toast
                
                Toast.makeText(
                    context, 
                    "Thank you for your feedback!", 
                    Toast.LENGTH_SHORT
                ).show()
                
                dialog.dismiss()
            }
            
            dialog.show()
        }
        
        /**
         * Shows the Privacy Policy dialog
         */
        fun showPrivacyPolicyDialog(context: Context) {
            val dialog = Dialog(context, android.R.style.Theme_Light_NoTitleBar_Fullscreen)
            dialog.setContentView(R.layout.dialog_privacy_policy)
            
            val closeButton = dialog.findViewById<ImageButton>(R.id.btn_close)
            closeButton.setOnClickListener {
                dialog.dismiss()
            }
            
            dialog.show()
        }
        
        /**
         * Shows the Terms and Conditions dialog
         */
        fun showTermsAndConditionsDialog(context: Context) {
            val dialog = Dialog(context, android.R.style.Theme_Light_NoTitleBar_Fullscreen)
            dialog.setContentView(R.layout.dialog_terms_conditions)
            
            val closeButton = dialog.findViewById<ImageButton>(R.id.btn_close)
            closeButton.setOnClickListener {
                dialog.dismiss()
            }
            
            dialog.show()
        }
        
        /**
         * Shows the How to Use dialog
         */
        fun showHowToUseDialog(context: Context) {
            val dialog = Dialog(context, android.R.style.Theme_Light_NoTitleBar_Fullscreen)
            dialog.setContentView(R.layout.dialog_how_to_use)
            
            val closeButton = dialog.findViewById<ImageButton>(R.id.btn_close)
            val startWalkthroughButton = dialog.findViewById<Button>(R.id.btn_start_walkthrough)
            
            closeButton.setOnClickListener {
                dialog.dismiss()
            }
            
            startWalkthroughButton.setOnClickListener {
                // In a real app, this would trigger a walkthrough experience
                Toast.makeText(context, "Starting walkthrough...", Toast.LENGTH_SHORT).show()
            }
            
            dialog.show()
        }
    }
}