package com.app.keepcount.utility

import android.content.Context
import android.content.SharedPreferences
import android.graphics.Color
import android.preference.PreferenceManager
import android.util.Log
import com.app.keepcount.utility.SharedPreferences.USER

abstract class SharedPreferenceUtil {

    companion object {
        var SharedPreference: SharedPreferences? = null

        fun initialize(context: Context): SharedPreferences {
            if (SharedPreference == null)
                SharedPreference = PreferenceManager.getDefaultSharedPreferences(context)
            return SharedPreference!!
        }

        fun saveUser(key: String) {
            val editor = SharedPreference!!.edit()
            editor.putString(USER, key)
            editor.apply()
        }

        fun getUser(): String? {
            // Log.e("SSO Key=",SharedPreference)
            return SharedPreference!!.getString(USER, "")
        }
    }

}