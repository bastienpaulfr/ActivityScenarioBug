package com.example.activityscenariobug.a

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.activityscenariobug.R
import com.example.activityscenariobug.b.ActivityB
import com.example.activityscenariobug.databinding.ActivityABinding
import com.example.activityscenariobug.databinding.ActivityMainBinding

class ActivityA : AppCompatActivity() {

    private lateinit var binding: ActivityABinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityABinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.button2.setOnClickListener {
            startActivity(Intent(this, ActivityB::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            })
        }
    }
}
