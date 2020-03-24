package com.tanmay.therona.ui.auth

import LoginFragment
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.tanmay.therona.R

class AuthActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, LoginFragment())
                .commitNow()
        }
    }
}
