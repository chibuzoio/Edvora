package com.example.edvora

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.edvora.adapter.ViewPagerAdapter
import com.example.edvora.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapter = ViewPagerAdapter(supportFragmentManager)
        binding.viewPager.adapter = adapter

        binding.tabLayout.setupWithViewPager(binding.viewPager)
    }
}


