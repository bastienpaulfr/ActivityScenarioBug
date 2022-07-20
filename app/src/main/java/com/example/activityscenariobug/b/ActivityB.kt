package com.example.activityscenariobug.b

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.activityscenariobug.c.ActivityC
import com.example.activityscenariobug.databinding.ActivityBBinding

class ActivityB : AppCompatActivity() {
    private lateinit var binding: ActivityBBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.button3.setOnClickListener {
            startActivity(Intent(this, ActivityC::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            })
        }
    }
}
