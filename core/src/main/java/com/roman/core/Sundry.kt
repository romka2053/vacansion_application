package com.roman.core

import android.view.LayoutInflater
import android.view.ViewGroup
import java.util.Locale

typealias Inflate<T> = (LayoutInflater, ViewGroup?, Boolean) -> T

fun String?.firstUpperCase(): String {
    if (this.isNullOrEmpty()) return ""
    return this.substring(0, 1).uppercase(Locale.getDefault()) + this.substring(1);
}