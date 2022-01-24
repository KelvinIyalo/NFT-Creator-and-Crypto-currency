package com.example.nativeandroiddesign.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.nativeandroiddesign.R
import com.example.nativeandroiddesign.databinding.FragmentDetailsBinding
import com.example.nativeandroiddesign.databinding.FragmentSignUpAndLoginBinding

class DetailsFragment : Fragment(R.layout.fragment_details) {
    private val args:DetailsFragmentArgs by navArgs()
    lateinit var binding: FragmentDetailsBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentDetailsBinding.inflate(layoutInflater)
        inflater.context.setTheme(R.style.Theme_MyFragment)

    val userDetails = args.userClass

        binding.userNameNFT.text = userDetails?.name




        return binding.root
    }

}