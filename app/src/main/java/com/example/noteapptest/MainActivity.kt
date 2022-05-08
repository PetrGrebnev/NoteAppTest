package com.example.noteapptest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.noteapptest.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val backStep: Boolean
        get() = supportFragmentManager.backStackEntryCount > 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.setDisplayHomeAsUpEnabled(backStep)
    }

    override fun onSupportNavigateUp(): Boolean {
        return if (backStep){
            supportFragmentManager.popBackStack()
            false
        } else {
            finish()
            true
        }
    }
}