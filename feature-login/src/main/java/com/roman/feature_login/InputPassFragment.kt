package com.roman.feature_login


import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.View
import androidx.appcompat.content.res.AppCompatResources
import androidx.appcompat.widget.AppCompatEditText
import androidx.core.widget.addTextChangedListener
import androidx.navigation.fragment.findNavController
import com.roman.core.BaseFragment
import com.roman.feature_login.databinding.FragmentInputPassBinding
import com.roman.navigation.Info
import com.roman.navigation.navigate
import com.roman.navigation.navigationData


class InputPassFragment :
    BaseFragment<FragmentInputPassBinding>(FragmentInputPassBinding::inflate) {
    private var email = ""
    private val mapPin = mutableMapOf<Int, Int?>()
    override val onViewCreatedFun: () -> Unit = ::setContent

    override val onCreateFun: () -> Unit = {

        email = (navigationData as? Info)?.valString ?: ""
    }


    private fun setContent() {
        binding.buttonConfirm.setOnClickListener {
            navigate(R.id.action_login_to_search)
        }

        initializationPin()
        setEmail()

    }

    private fun setEmail() {
        val text = getString(R.string.sent_massage_to_mail) + " " + email
        binding.emailText.text = text
    }

    private fun initializationPin() {
        val listPin = listOf(binding.pin1, binding.pin2, binding.pin3, binding.pin4)
        listPin.forEachIndexed { index, editText ->
            focusBackgroundPin(editText)
            mapPin[editText.id] = editText.text.toString().toIntOrNull()
            val nextEditOrNull = listPin.getOrNull(index + 1)
            val prevEditOrNull = listPin.getOrNull(index - 1)
            nextPin(editText, nextEditOrNull, prevEditOrNull)
        }
    }

    private fun nextPin(
        pinElement: AppCompatEditText,
        nextPinElement: AppCompatEditText?,
        prevPinElement: AppCompatEditText?
    ) {
        pinElement.setOnKeyListener { view, p1, keyEvent ->
            if (keyEvent.action == KeyEvent.ACTION_UP) {
                view as AppCompatEditText
                if (keyEvent.keyCode == KeyEvent.KEYCODE_DEL) {

                    if (view.text.toString() != "") {
                        mapPin[view.id] = null
                        view.setText("")
                    } else {
                        prevPinElement?.let {
                            it.requestFocus()
                            it.setText("")
                            mapPin[it.id] = null
                        }
                    }
                } else {
                    mapPin[pinElement.id] = keyEvent.number.toString().toIntOrNull()
                    view.setText(keyEvent.number.toString())
                    view.setSelection(1)
                    nextPinElement?.requestFocus()


                }

            }
            var complete = true
            for (pin in mapPin) {
                if (pin.value == null) {
                    complete = false
                    break
                }
            }
            binding.buttonConfirm.isEnabled = complete
            return@setOnKeyListener true
        }
    }

    private fun focusBackgroundPin(pinElement: AppCompatEditText) {

        pinElement.setOnFocusChangeListener { view, focus ->
            view as AppCompatEditText
            view.background = if (!focus && view.text.toString() == "") {
                AppCompatResources.getDrawable(
                    requireContext(),
                    R.drawable.pin
                )
            } else {
                AppCompatResources.getDrawable(
                    requireContext(),
                    com.roman.core_ui.R.drawable.input_field_background
                )
            }
        }
    }
}