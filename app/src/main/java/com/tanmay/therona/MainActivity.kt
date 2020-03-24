package com.tanmay.therona

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.tanmay.therona.ui.boards.BoardsFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, BoardsFragment.newInstance())
                .commitNow()
        }
    }
}
