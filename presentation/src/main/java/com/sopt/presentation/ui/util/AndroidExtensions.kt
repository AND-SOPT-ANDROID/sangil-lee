package com.sopt.presentation.ui.util

import android.os.Build
import android.os.Bundle

internal inline fun<reified T> Bundle.getParcelableCompat(key: String): T? {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        getParcelable(key, T::class.java)
    } else {
        getParcelable(key)
    }
}