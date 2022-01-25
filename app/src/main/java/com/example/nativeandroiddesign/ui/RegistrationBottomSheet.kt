package com.example.nativeandroiddesign.ui

import android.app.Activity
import android.content.AbstractThreadedSyncAdapter
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.navigation.fragment.NavHostFragment.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.nativeandroiddesign.R
import com.example.nativeandroiddesign.adapter.RecyclerViewAdapter
import com.example.nativeandroiddesign.viewmodel.MyViewModel
import com.example.testdesign.registrationUI
import com.google.android.material.bottomsheet.BottomSheetDialog
import kotlin.coroutines.coroutineContext

fun registrationSheetSetup1(
    context: Activity,myViewModel: MyViewModel,myAdapter: RecyclerViewAdapter
) {

    val bottomSheetDialog = BottomSheetDialog(context, R.style.bottomSheetDialogTheme)
    val bottomSheetView = LayoutInflater.from(context).inflate(
        R.layout.register_layout, context.findViewById<ConstraintLayout?>(R.id.registration_id)
    )
    bottomSheetView.findViewById<View>(R.id.btn_dismiss).setOnClickListener {
        bottomSheetDialog.dismiss()
        Toast.makeText(context, "Registration Canceled", Toast.LENGTH_SHORT).show()
    }
    bottomSheetView.findViewById<View>(R.id.login_btn).setOnClickListener {
       sendGiftBottomSheet(context,myViewModel,myAdapter)
     //  myViewModelSetup()
        bottomSheetDialog.dismiss()
    }
    val fullName = bottomSheetView.findViewById<EditText>(R.id.signup_fullname_ET)
    val fullNameLabel = bottomSheetView.findViewById<TextView>(R.id.fullname_label)
    val walletID = bottomSheetView.findViewById<EditText>(R.id.signup_walletId_ET)
    val walletIDLabel = bottomSheetView.findViewById<TextView>(R.id.walletId_label)
    val btnCreate = bottomSheetView.findViewById<Button>(R.id.btn_createAccount)


    registrationUI(fullName, fullNameLabel, walletID, walletIDLabel, btnCreate)


    bottomSheetDialog.setContentView(bottomSheetView)
    bottomSheetDialog.show()

}

fun sendGiftBottomSheet(context: Activity, myViewModel: MyViewModel,myAdapter: RecyclerViewAdapter) {

    val sendGiftBottomSheet =
        BottomSheetDialog(context, R.style.bottomSheetDialogTheme)
    val sendGiftBottomSheetView = LayoutInflater.from(context).inflate(
        R.layout.gift_layout,
        context.findViewById<ConstraintLayout?>(R.id.sendGift_screen)
    )
    val recyclerView =
        sendGiftBottomSheetView.findViewById<RecyclerView>(R.id.recyclerView_gift)

    sendGiftBottomSheetView.findViewById<View>(R.id.btn_dismiss).setOnClickListener {
        sendGiftBottomSheet.dismiss()
        Toast.makeText(context, "Registration Canceled", Toast.LENGTH_SHORT).show()
    }


  //  recyclerView.apply {
  //      adapter = myAdapter
  //      layoutManager = LinearLayoutMan
  //  }

    myAdapter.setOnItemClickListener {
        myViewModel.Updert(it)
        val bundle = Bundle().apply { putSerializable("users", it) }

      // findNavController().navigate(
      //     R.id.action_signUpAndLoginFragment_to_detailsFragment,
      //     bundle
      // )
        sendGiftBottomSheet.dismiss()
    }

    sendGiftBottomSheet.setContentView(sendGiftBottomSheetView)
    sendGiftBottomSheet.show()
}
