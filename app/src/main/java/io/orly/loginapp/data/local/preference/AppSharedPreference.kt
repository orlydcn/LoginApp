package io.orly.loginapp.data.local.preference

import android.content.SharedPreferences
import androidx.core.content.edit
import io.orly.loginapp.util.AppConstant.PREF_EMAIL
import io.orly.loginapp.util.AppConstant.PREF_ID
import javax.inject.Inject

class AppSharedPreference @Inject constructor(private val mPrefs: SharedPreferences) {
    fun clear() = mPrefs.edit().clear().commit()

    fun saveId(id: Int) = mPrefs.edit {
        putInt(PREF_ID, id)
    }

    fun getId() = mPrefs.getInt(PREF_ID, 0)

    fun saveEmail(email: String) = mPrefs.edit {
        putString(PREF_EMAIL, email)
    }

    fun getEmail(): String? = mPrefs.getString(PREF_EMAIL, null)

}