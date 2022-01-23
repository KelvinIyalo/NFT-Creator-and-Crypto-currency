package com.example.nativeandroiddesign.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import com.example.nativeandroiddesign.R
import com.example.nativeandroiddesign.adapter.RecyclerViewAdapter
import com.example.nativeandroiddesign.databinding.FragmentSignUpAndLoginBinding
import com.example.testdesign.navigationUI
import com.example.testdesign.registrationUI
import com.google.android.material.bottomsheet.BottomSheetDialog

class SignUpAndLoginFragment : Fragment(R.layout.fragment_sign_up_and_login) {
    lateinit var binding: FragmentSignUpAndLoginBinding

    //  var viewModel: MyViewModel by viewModels()
    lateinit var MyAdapter: RecyclerViewAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSignUpAndLoginBinding.inflate(layoutInflater)



        binding.apply {
            navigationUI(
                signupEmailET,
                loginWebET,
                btnGetStarted,
                signupPhoneET,
                navPhone,
                navEmail
            )
        }

        binding.btnGetStarted.setOnClickListener {
            registrationSheetSetup()
        }
        binding.termsCondition.setOnClickListener {
            Toast.makeText(activity, "This is Experimental", Toast.LENGTH_SHORT).show()
        }


        return binding.root
    }

    fun registrationSheetSetup() {

        val bottomSheet = BottomSheetDialog(requireContext(), R.style.bottomSheetDialogTheme)
        val bottomSheetView = LayoutInflater.from(context)
            .inflate(
                R.layout.register_layout,
                view?.findViewById<ConstraintLayout?>(R.id.registration_id)
            )
        bottomSheetView.findViewById<View>(R.id.btn_dismiss).setOnClickListener {
            bottomSheet.dismiss()
            Toast.makeText(activity, "Registration Canceled", Toast.LENGTH_SHORT).show()
        }
        bottomSheetView.findViewById<View>(R.id.login_btn).setOnClickListener {
            sendGiftBottomSheet()
        }
        val fullName = bottomSheetView.findViewById<EditText>(R.id.signup_fullname_ET)
        val fullNameLabel = bottomSheetView.findViewById<TextView>(R.id.fullname_label)
        val walletID = bottomSheetView.findViewById<EditText>(R.id.signup_walletId_ET)
        val walletIDLabel = bottomSheetView.findViewById<TextView>(R.id.walletId_label)
        val btnCreate = bottomSheetView.findViewById<Button>(R.id.btn_createAccount)


        registrationUI(fullName, fullNameLabel, walletID, walletIDLabel, btnCreate)


        bottomSheet.setContentView(bottomSheetView)
        bottomSheet.show()
    }

    fun sendGiftBottomSheet() {

        val sendGiftBottomSheet =
            BottomSheetDialog(requireContext(), R.style.bottomSheetDialogTheme)
        val sendGiftBottomSheetView = LayoutInflater.from(context)
            .inflate(
                R.layout.gift_layout,
                view?.findViewById<ConstraintLayout?>(R.id.sendGift_screen)
            )
        sendGiftBottomSheetView.findViewById<View>(R.id.btn_dismiss).setOnClickListener {
            sendGiftBottomSheet.dismiss()
            Toast.makeText(activity, "Registration Canceled", Toast.LENGTH_SHORT).show()
        }
        sendGiftBottomSheet.setContentView(sendGiftBottomSheetView)
        sendGiftBottomSheet.show()
    }
}