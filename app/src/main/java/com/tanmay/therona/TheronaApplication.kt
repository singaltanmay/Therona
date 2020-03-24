package com.tanmay.therona

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit

class TheronaApplication : Application() {

    companion object {
        val SHARED_PREFERENCES_KEY = "com.tanmay.workboards_preferences"
        val USER_LOGGED_IN_KEY = "user_logged_in"
        lateinit var instance: TheronaApplication
            private set
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    fun getAppPreferences(): SharedPreferences = instance.getSharedPreferences(
        SHARED_PREFERENCES_KEY,
        Context.MODE_PRIVATE
    )

    var userLoggedIn
        get() = getAppPreferences().getBoolean(USER_LOGGED_IN_KEY, false)
        set(value) = getAppPreferences().edit { putBoolean(USER_LOGGED_IN_KEY, value) }

    var user: User
        get() {
            val prefs = getAppPreferences()
            return User(
                prefs.getString(User.KEY_FIRST_NAME, null),
                prefs.getString(User.KEY_LAST_NAME, null),
                prefs.getString(User.KEY_EMAIL, "")!!,
                prefs.getString(User.KEY_PASSWORD, null)
            )
        }
        set(value) = getAppPreferences().edit {
            putString(User.KEY_FIRST_NAME, value.firstName)
            putString(User.KEY_LAST_NAME, value.lastName)
            putString(User.KEY_EMAIL, value.email)
            putString(User.KEY_PASSWORD, value.password)
        }


    data class User(
        val firstName: String? = null,
        val lastName: String? = null,
        val email: String,
        val password: String? = null
    ) {
        companion object {
            val KEY_FIRST_NAME: String = "user_first_name"
            val KEY_LAST_NAME: String = "user_last_name"
            val KEY_EMAIL = "user_email_address"
            val KEY_PASSWORD = "user_password"
        }
    }


}