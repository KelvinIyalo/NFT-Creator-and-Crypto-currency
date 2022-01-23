package com.example.nativeandroiddesign.ui

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContentProviderCompat.requireContext
import com.example.nativeandroiddesign.R
import com.example.testdesign.registrationUI
import com.google.android.material.bottomsheet.BottomSheetDialog
import kotlin.coroutines.coroutineContext

fun registrationSheetSetup(context:Activity,bottomsheet: BottomSheetDialog, bottomSheetView: View) {

    BottomSheetDialog(context, R.style.bottomSheetDialogTheme)
    LayoutInflater.from(context)
        .inflate(
            R.layout.register_layout,
            bottomSheetView?.findViewById<ConstraintLayout?>(R.id.registration_id)
        )
    bottomSheetView.findViewById<View>(R.id.btn_dismiss).setOnClickListener {
        bottomsheet.dismiss()
        Toast.makeText(context, "Registration Canceled", Toast.LENGTH_SHORT).show()
    }
    bottomSheetView.findViewById<View>(R.id.login_btn).setOnClickListener {
      //  sendGiftBottomSheet()
    }
    val fullName = bottomSheetView.findViewById<EditText>(R.id.signup_fullname_ET)
    val fullNameLabel = bottomSheetView.findViewById<TextView>(R.id.fullname_label)
    val walletID = bottomSheetView.findViewById<EditText>(R.id.signup_walletId_ET)
    val walletIDLabel = bottomSheetView.findViewById<TextView>(R.id.walletId_label)
    val btnCreate = bottomSheetView.findViewById<Button>(R.id.btn_createAccount)


    registrationUI(fullName, fullNameLabel, walletID, walletIDLabel, btnCreate)


    bottomsheet.setContentView(bottomSheetView)
    bottomsheet.show()

}