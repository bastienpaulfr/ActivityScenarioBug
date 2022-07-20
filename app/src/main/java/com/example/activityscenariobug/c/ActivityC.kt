package com.example.activityscenariobug.c

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.activityscenariobug.databinding.ActivityCBinding

class ActivityC : AppCompatActivity() {
    private lateinit var binding: ActivityCBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }
}
