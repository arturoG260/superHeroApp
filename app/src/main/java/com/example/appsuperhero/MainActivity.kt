package com.example.appsuperhero

import android.os.Bundle
import com.example.appsuperhero.utils.UTBaseActivity
import com.example.appsuperhero.views.ListHeros

class MainActivity : UTBaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mostrarFragment(ListHeros::class.java, R.id.clContenedor, null, false)
    }
}