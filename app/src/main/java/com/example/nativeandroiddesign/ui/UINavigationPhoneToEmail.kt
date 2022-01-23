package com.example.testdesign

import android.graphics.Color
import android.view.View
import android.widget.EditText
import android.widget.TextView
import com.example.nativeandroiddesign.R

fun switchToEmail(
    signupEmailET: EditText,
    navPhone: TextView,
    navEmail: TextView,
    signupPhoneET: EditText
) {
    navEmail.apply {
        setBackgroundResource(R.drawable.btn_bg_gray)
        setTextColor(Color.BLACK)
        elevation = 5f
    }
    navPhone.apply {
        setTextColor(Color.GRAY)
        setBackgroundResource(R.drawable.nav_header_empty)
        elevation = 0f
    }
    signupEmailET.apply {
        animate().alpha(1f).duration = 400L
        visibility = View.VISIBLE
    }
    signupPhoneET.apply {
        animate().alpha(0f).duration = 400L
        visibility = View.GONE
        this.text = null
    }


}

fun switchToPhone(
    signupEmailET: EditText,
    navPhone: TextView,
    navEmail: TextView,
    signupPhoneET: EditText
) {
    navPhone.apply {
        setTextColor(Color.BLACK)
        setBackgroundResource(R.drawable.btn_bg_gray)
        elevation = 5f
    }
    signupEmailET.apply {
        animate().alpha(0f).duration = 400L
        visibility = View.GONE
        this.text = null
    }
    navEmail.apply {
        setTextColor(Color.GRAY)
        setBackgroundResource(R.drawable.nav_header_empty)
        elevation = 0f
    }
    signupPhoneET.apply {
        visibility = View.VISIBLE
        animate().alpha(1f).duration = 400L
    }

}