package com.roman.navigation

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.navigation.NavAction
import androidx.navigation.NavOptions
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import java.io.Serializable



fun Fragment.navigate(actionId: Int, hostId: Int? = null, data: Serializable? = null) {

    val navController = if (hostId == null) {
        findNavController()
    } else {
        Navigation.findNavController(requireActivity(), hostId)
    }

    val bundle = Bundle().apply { putSerializable("navigation data", data) }
    navController.navigate(actionId, bundle)
}

val Fragment.navigationData: Serializable?
    get() = arguments?.getSerializable("navigation data")

data class Info(
    val valString: String?=null,
    val surname: String?=null
) : Serializable