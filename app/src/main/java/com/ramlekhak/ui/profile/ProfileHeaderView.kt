package com.ramlekhak.ui.profile

import android.content.Context
import android.text.TextUtils
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.android.material.snackbar.Snackbar
import com.ramlekhak.R

class ProfileHeaderView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : CardView(context, attrs, defStyleAttr) {

    private val avatarCircle: CardView
    private val initialsText: TextView
    private val normalStateContainer: ConstraintLayout
    private val editStateContainer: ConstraintLayout
    private val nameText: TextView
    private val editNameButton: ImageView
    private val nameEditText: EditText
    private val confirmButton: CardView
    private val cancelButton: CardView
    private val memberSinceText: TextView

    private var currentName: String = "Ram Bhakt"
    private var onNameChangedListener: ((String) -> Unit)? = null

    init {
        // Inflate the layout
        LayoutInflater.from(context).inflate(R.layout.view_profile_header, this, true)

        // Initialize views
        avatarCircle = findViewById(R.id.avatarCircle)
        initialsText = findViewById(R.id.initialsText)
        normalStateContainer = findViewById(R.id.normalStateContainer)
        editStateContainer = findViewById(R.id.editStateContainer)
        nameText = findViewById(R.id.nameText)
        editNameButton = findViewById(R.id.editNameButton)
        nameEditText = findViewById(R.id.nameEditText)
        confirmButton = findViewById(R.id.confirmButton)
        cancelButton = findViewById(R.id.cancelButton)
        memberSinceText = findViewById(R.id.memberSinceText)

        // Set initial state
        showNormalState()
        updateInitials(currentName)

        // Set click listeners
        setupClickListeners()
    }

    private fun setupClickListeners() {
        // Edit button click
        editNameButton.setOnClickListener {
            showEditState()
        }

        // Confirm button click
        confirmButton.setOnClickListener {
            val newName = nameEditText.text.toString().trim()
            if (validateName(newName)) {
                updateName(newName)
                showNormalState()
                showSuccessMessage()
            }
        }

        // Cancel button click
        cancelButton.setOnClickListener {
            nameEditText.setText(currentName)
            showNormalState()
        }
    }

    private fun validateName(name: String): Boolean {
        if (TextUtils.isEmpty(name)) {
            nameEditText.error = "Name cannot be empty"
            return false
        }
        return true
    }

    private fun updateName(newName: String) {
        currentName = newName
        nameText.text = newName
        updateInitials(newName)
        onNameChangedListener?.invoke(newName)
    }

    private fun updateInitials(name: String) {
        val parts = name.split(" ")
        val initials = if (parts.size >= 2) {
            // Use first letter of first and last name
            "${parts[0].firstOrNull() ?: ""}${parts[parts.size - 1].firstOrNull() ?: ""}"
        } else {
            // Use first letter if only one name
            parts[0].take(2)
        }.uppercase()

        initialsText.text = initials
    }

    private fun showNormalState() {
        normalStateContainer.visibility = View.VISIBLE
        editStateContainer.visibility = View.GONE
    }

    private fun showEditState() {
        normalStateContainer.visibility = View.GONE
        editStateContainer.visibility = View.VISIBLE
        nameEditText.setText(currentName)
        nameEditText.requestFocus()
        nameEditText.setSelection(currentName.length)
    }

    private fun showSuccessMessage() {
        Snackbar.make(this, "Name updated successfully", Snackbar.LENGTH_SHORT).show()
    }

    fun setName(name: String) {
        currentName = name
        nameText.text = name
        updateInitials(name)
    }

    fun getName(): String {
        return currentName
    }

    fun setMemberSince(memberSince: String) {
        memberSinceText.text = "Member since: $memberSince"
    }

    fun setOnNameChangedListener(listener: (String) -> Unit) {
        onNameChangedListener = listener
    }
} 