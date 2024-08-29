package com.roman.feature_login


import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.view.View
import android.view.WindowInsetsAnimation.Bounds
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.widget.addTextChangedListener
import androidx.navigation.fragment.findNavController
import com.roman.core.BaseFragment
import com.roman.feature_login.databinding.FragmentLoginBinding
import com.roman.navigation.Info
import com.roman.navigation.navigate


class LoginFragment : BaseFragment<FragmentLoginBinding>(FragmentLoginBinding::inflate) {


    override val onViewCreatedFun: () -> Unit = ::setContent


    private fun setContent() {
        editTextChange()
        clickContinue()
    }

    private fun clickContinue() {
        binding.buttonContinue.setOnClickListener {
            val email = binding.editTextLogin.text.toString()
            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                binding.errorText.visibility = View.VISIBLE
                binding.editTextLogin.background = AppCompatResources.getDrawable(
                    requireContext(),
                    com.roman.core_ui.R.drawable.input_field_background_error
                )
            } else {
                navigate(R.id.action_loginFragment_to_inputPassFragment, data = Info(email))
            }
        }
    }

    private fun editTextChange() {
        val drawable =
            AppCompatResources.getDrawable(requireContext(), com.roman.core_ui.R.drawable.responses)
        binding.closeImage.setOnClickListener {
            binding.editTextLogin.text?.clear()
        }
        binding.editTextLogin.addTextChangedListener { text ->
            binding.errorText.visibility = View.GONE
            binding.editTextLogin.background = AppCompatResources.getDrawable(
                requireContext(),
                com.roman.core_ui.R.drawable.input_field_background
            )

            if (text.toString() == "") {
                binding.closeImage.visibility = View.GONE
                binding.editTextLogin.setCompoundDrawablesWithIntrinsicBounds(
                    drawable, null, null, null
                )
                binding.buttonContinue.isEnabled = false
            } else {
                binding.closeImage.visibility = View.VISIBLE
                binding.editTextLogin.setCompoundDrawables(null, null, null, null)
                binding.buttonContinue.isEnabled = true
            }
        }
    }

}

