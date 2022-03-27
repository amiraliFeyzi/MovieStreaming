package com.example.moviestreaming.view.auth.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.example.moviestreaming.R
import com.example.moviestreaming.databinding.FragmentSignUpBinding
import com.example.moviestreaming.utils.variables.EXTRA_KEY_DATA
import com.example.moviestreaming.view.auth.AuthViewModel
import com.example.moviestreaming.view.home.MainActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignUpFragment : Fragment() {

    private var _binding: FragmentSignUpBinding? = null
    private val binding get() =  _binding!!

    private val viewModel: AuthViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View{
        // Inflate the layout for this fragment
        _binding = FragmentSignUpBinding.inflate(inflater , container ,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        signUp()
    }


    private fun signUp(){
        binding.tvHaveAccount.setOnClickListener {
            findNavController().navigate(R.id.action_signUpFragment_to_logInFragment)
        }

        binding.btnSignUp.setOnClickListener {
            if (checkEditTextValue()) {
                viewModel.signUP(binding.etEmail.text.toString(),binding.etPhone.text.toString()  ,binding.etPassword.text.toString())
            }

        }

        observeData()
    }

    private fun observeData(){
        viewModel.signUpLiveData.observe(viewLifecycleOwner){
            if (it.authSuccessCompleted){
                startActivity(Intent(requireContext() , MainActivity::class.java))
                requireActivity().finish()
            }else{
                Toast.makeText(requireContext() , getString(it.message) , Toast.LENGTH_SHORT).show()
            }
        }
    }


    private fun checkEditTextValue():Boolean{
        return if (binding.etEmail.text.isNullOrEmpty() && binding.etPassword.text.isNullOrEmpty() && binding.etPhone.text.isNullOrEmpty()){
            Toast.makeText(requireContext() , getString(R.string.valueInput) , Toast.LENGTH_SHORT).show()
            false
        }else{
            true
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }



}