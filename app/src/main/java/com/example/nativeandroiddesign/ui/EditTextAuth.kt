package com.example.testdesign

import android.graphics.Color
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.core.widget.addTextChangedListener
import com.example.nativeandroiddesign.R

private fun editTextAuth(
    signupEmailET: EditText,
    loginWebET: EditText,
    btnGetStarted:Button,
    signupPhoneET: EditText


) {

    signupEmailET.addTextChangedListener{
        if (it.toString().length > 0){
            signupEmailET.setBackgroundResource(R.drawable.bg_focus)

            if (it.toString().length > 5){
                btnGetStarted.isEnabled = true
                btnGetStarted.setBackgroundColor(Color.BLUE)
            }else{
                btnGetStarted.isEnabled = false
            }
        }else{
            signupEmailET.setBackgroundResource(R.drawable.et_bg)
        }


    }

    signupPhoneET.addTextChangedListener{
        if (it.toString().length > 0){
            signupPhoneET.setBackgroundResource(R.drawable.bg_focus)
            if (it.toString().length > 5){
                btnGetStarted.isEnabled = true
                btnGetStarted.setBackgroundColor(Color.BLUE)
            }else{
                btnGetStarted.isEnabled = false
            }

        }else{
            signupPhoneET.setBackgroundResource(R.drawable.et_bg)
        }
    }

    loginWebET.addTextChangedListener{
        if (it.toString().length > 0){
            loginWebET.setBackgroundResource(R.drawable.bg_focus)
        }else{
            loginWebET.setBackgroundResource(R.drawable.et_bg)
        }
    }


}

fun navigationUI(
    signupEmailET: EditText,
    loginWebET: EditText,
    btnGetStarted:Button,
    signupPhoneET: EditText,
    navPhone: TextView,
    navEmail: TextView
) {
    navPhone.setOnClickListener {

            switchToPhone(signupEmailET,navPhone,navEmail,signupPhoneET)


    }

    navEmail.setOnClickListener {

            switchToEmail(signupEmailET,navPhone,navEmail,signupPhoneET)

    }

    //for EditTextAuth
    editTextAuth(signupEmailET,loginWebET,btnGetStarted,signupPhoneET)

}

fun registrationUI(
    fullName:EditText,
    fullNameLabel:TextView,
    walletId:EditText,
    walletIdLabel:TextView,
    btnGetStarted:Button

){

    fullName.addTextChangedListener{
        if (it.toString().length > 0){
            fullName.setBackgroundResource(R.drawable.bg_focus)
            fullNameLabel.setTextColor(Color.BLUE)
        }else{
            fullName.setBackgroundResource(R.drawable.et_bg)
        }
    }

    walletId.addTextChangedListener{
        if (it.toString().length > 0){
            walletId.setBackgroundResource(R.drawable.bg_focus)
            walletIdLabel.setTextColor(Color.BLUE)
        }else{
            fullName.setBackgroundResource(R.drawable.et_bg)
        }
    }

    if(walletId.text.toString().length > 4  && walletId.text.toString().length > 4 ){
        btnGetStarted.isEnabled = true
        btnGetStarted.setBackgroundColor(Color.BLUE)

    }else{
        btnGetStarted.isEnabled = false
    }


}