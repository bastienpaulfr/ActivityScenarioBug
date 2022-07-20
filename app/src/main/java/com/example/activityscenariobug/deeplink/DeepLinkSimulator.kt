package com.example.activityscenariobug.deeplink

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.activityscenariobug.R
import com.example.activityscenariobug.c.ActivityC

class DeepLinkSimulator : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onStart() {
        super.onStart()
        startActivity(Intent(this, ActivityC::class.java))
        finish()
    }
}
