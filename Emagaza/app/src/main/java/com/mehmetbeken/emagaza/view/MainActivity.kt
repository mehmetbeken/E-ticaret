package com.mehmetbeken.emagaza.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.Navigation
import androidx.navigation.ui.setupWithNavController
import com.mehmetbeken.emagaza.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navController = Navigation.findNavController(this, R.id.fragment)
        bottomNavigationView.setupWithNavController(navController)

    }
}