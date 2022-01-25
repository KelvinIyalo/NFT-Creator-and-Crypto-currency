package com.example.nativeandroiddesign.ui

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.nativeandroiddesign.R
import com.example.nativeandroiddesign.adapter.RecyclerViewAdapter
import com.example.nativeandroiddesign.databinding.FragmentSignUpAndLoginBinding
import com.example.nativeandroiddesign.utill.Resource
import com.example.nativeandroiddesign.viewmodel.MyViewModel
import com.example.testdesign.navigationUI
import com.example.testdesign.registrationUI
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignUpAndLoginFragment : Fragment(R.layout.fragment_sign_up_and_login) {
    lateinit var binding: FragmentSignUpAndLoginBinding

    val viewModel: MyViewModel by viewModels()
    lateinit var MyAdapter: RecyclerViewAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSignUpAndLoginBinding.inflate(layoutInflater)

        binding.btnGetStarted.setOnClickListener {
            registrationSheetSetup()

            MyAdapter = RecyclerViewAdapter()

        }


        //Navigation/UI Authentication
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

        binding.termsCondition.setOnClickListener {
            Toast.makeText(activity, "This is Experimental", Toast.LENGTH_SHORT).show()
        }


        return binding.root
    }

    //Registration Bottom Sheet
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
            myViewModelSetup()
            bottomSheet.dismiss()
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

    //RecyclerView Bottom Sheet
    fun sendGiftBottomSheet() {

        val sendGiftBottomSheet =
            BottomSheetDialog(requireContext(), R.style.bottomSheetDialogTheme)
        val sendGiftBottomSheetView = LayoutInflater.from(context).inflate(
            R.layout.gift_layout,
            view?.findViewById<ConstraintLayout?>(R.id.sendGift_screen)
        )
        val recyclerView =
            sendGiftBottomSheetView.findViewById<RecyclerView>(R.id.recyclerView_gift)

        sendGiftBottomSheetView.findViewById<View>(R.id.btn_dismiss).setOnClickListener {
            sendGiftBottomSheet.dismiss()
            Toast.makeText(activity, "Registration Canceled", Toast.LENGTH_SHORT).show()
        }
   // val search = sendGiftBottomSheetView.findViewById<EditText>(R.id.search_text)


        recyclerView.apply {
            adapter = MyAdapter
            layoutManager = LinearLayoutManager(activity)
        }

        MyAdapter.setOnItemClickListener {
             viewModel.Updert(it)
            val bundle = Bundle().apply { putSerializable("users", it) }

            findNavController().navigate(
                R.id.action_signUpAndLoginFragment_to_detailsFragment,
                bundle
            )
            sendGiftBottomSheet.dismiss()
        }

        sendGiftBottomSheet.setContentView(sendGiftBottomSheetView)
        sendGiftBottomSheet.show()
    }

    fun myViewModelSetup() {
        viewModel.userList.observe(viewLifecycleOwner, Observer { response ->
            when (response) {
                is Resource.Loading -> {
                    Toast.makeText(activity, "loading...", Toast.LENGTH_SHORT).show()
                }

                is Resource.Success -> {

                    response.data?.let {    result ->

                        MyAdapter.differ.submitList(result.toList())


                    }
                }
                is Resource.Error -> {
                    response.Message?.let { message ->
                        Snackbar.make(
                            binding.root,
                            "An Error occurred:$message ",
                            Snackbar.LENGTH_SHORT
                        ).show()
                    }
                }

            }

        })
    }
}