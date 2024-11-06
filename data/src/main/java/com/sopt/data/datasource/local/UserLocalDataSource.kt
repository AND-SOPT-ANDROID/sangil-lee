package com.sopt.data.datasource.local

import android.content.SharedPreferences
import com.sopt.data.UserSharedPref
import javax.inject.Inject

class UserLocalDataSource @Inject constructor(
    @UserSharedPref private val userSharedPreferences: SharedPreferences,
) {

}