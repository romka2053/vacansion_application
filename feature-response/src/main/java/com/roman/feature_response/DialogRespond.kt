package com.roman.feature_response

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import com.roman.feature_response.databinding.DialogWindowBinding
import com.roman.feature_response_api.DialogRespondApi

class DialogRespond() : DialogFragment(), DialogRespondApi {
    private var _binding: DialogWindowBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DialogWindowBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.addMassage.setOnClickListener {
            binding.addMassage.visibility = View.GONE
            binding.editText.visibility = View.VISIBLE

        }

        binding.nameVacancy.text = tag
        binding.buttonRespond.setOnClickListener {
            dismiss()
        }
    }


}