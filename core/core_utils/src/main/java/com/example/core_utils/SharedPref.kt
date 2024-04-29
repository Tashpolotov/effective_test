package com.example.core_utils

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class SharedPref @Inject constructor(@ApplicationContext context: Context) {

    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)

    companion object {
        private const val PREF_NAME = "MyPrefs"
        private const val CITY = "city"

        private const val USER_CITY = "user_city"
    }

    var selectedName: String?
        get() = sharedPreferences.getString(CITY, null)
        set(value) {
            sharedPreferences.edit().putString(CITY, value).apply()
            Log.d("SharedPref", "Saved city: $value")
        }

    var selectedUserCity:String?
        get() = sharedPreferences.getString(USER_CITY, null)
        set(value) = sharedPreferences.edit().putString(USER_CITY, value).apply()
}


