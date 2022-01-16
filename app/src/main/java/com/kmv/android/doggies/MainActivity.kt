package com.kmv.android.doggies

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.kmv.android.doggies.fragments.AuthorizationFragment
import com.kmv.android.doggies.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager
                .beginTransaction()
                .add(R.id.container, AuthorizationFragment())
                .commit()
    }
}