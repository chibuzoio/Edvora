package com.example.edvora

import android.R
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.example.edvora.adapter.ViewPagerAdapter
import com.example.edvora.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var cityAdapter: ArrayAdapter<String?>
    private lateinit var stateAdapter: ArrayAdapter<String?>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapter = ViewPagerAdapter(supportFragmentManager)
        binding.viewPager.adapter = adapter

        binding.tabLayout.setupWithViewPager(binding.viewPager)

        binding.floatingMenuOverlay.setOnClickListener {
            binding.dropdownCityList.visibility = View.GONE
            binding.dropdownStateList.visibility = View.GONE
            binding.filterMenuLayout.visibility = View.GONE
            binding.floatingMenuOverlay.visibility = View.GONE
        }

        binding.filterMenuButton.setOnClickListener {
            binding.dropdownCityList.visibility = View.GONE
            binding.dropdownStateList.visibility = View.GONE
            binding.filterMenuLayout.visibility = View.VISIBLE
            binding.floatingMenuOverlay.visibility = View.VISIBLE
        }

        binding.stateFormLayout.setOnClickListener {
            binding.dropdownCityList.visibility = View.GONE

            if (binding.dropdownStateList.isVisible) {
                binding.dropdownStateList.visibility = View.GONE
            } else {
                binding.dropdownStateList.visibility = View.VISIBLE
            }
        }

        binding.cityFormLayout.setOnClickListener {
            binding.dropdownStateList.visibility = View.GONE

            if (binding.dropdownCityList.isVisible) {
                binding.dropdownCityList.visibility = View.GONE
            } else {
                binding.dropdownCityList.visibility = View.VISIBLE
            }
        }

        stateAdapter = ArrayAdapter(this, R.layout.simple_list_item_1, arrayOfNulls(0))
        binding.stateListView.adapter = stateAdapter

        cityAdapter = ArrayAdapter(this, R.layout.simple_list_item_1, arrayOfNulls(0))
        binding.cityListView.adapter = cityAdapter
    }
    
    fun populateFragmentData(cityList: Array<String?>, stateList: Array<String?>) {
        Log.e("Execution", "Execution 1")
        stateAdapter = ArrayAdapter(this, R.layout.simple_list_item_1, stateList)
        Log.e("Execution", "Execution 2")
        binding.stateListView.adapter = stateAdapter
        Log.e("Execution", "Execution 3")

        cityAdapter = ArrayAdapter(this, R.layout.simple_list_item_1, cityList)
        Log.e("Execution", "Execution 4")
        binding.cityListView.adapter = cityAdapter
        Log.e("Execution", "Execution 5")
    }
}


