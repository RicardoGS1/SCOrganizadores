package com.virtualworld.scorganizadores.common

import android.content.SharedPreferences
import javax.inject.Inject

class TokenManager @Inject constructor(
    private val sharedPref: SharedPreferences,
) {
    fun saveToken(token: String) {
        sharedPref.edit()
            .putString(Constants.PREF_FIREBASE_USERID_KEY, token)
            .apply()
    }

    fun getToken(): String? {
        return sharedPref.getString(Constants.PREF_FIREBASE_USERID_KEY,  Constants.PREF_DEF_STR)
    }



    fun deleteToken() {
        sharedPref.edit().remove(Constants.PREF_FIREBASE_USERID_KEY).apply()

    }



}
