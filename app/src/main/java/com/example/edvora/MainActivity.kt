package com.example.edvora

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.edvora.adapter.PlacesCollectionAdapter
import com.example.edvora.adapter.ViewPagerAdapter
import com.example.edvora.databinding.ActivityMainBinding
import com.example.edvora.model.PlacesCollectionModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

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

        binding.stateListRecycler.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        binding.stateListRecycler.itemAnimator = DefaultItemAnimator()

        binding.cityListRecycler.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        binding.cityListRecycler.itemAnimator = DefaultItemAnimator()

        binding.stateListRecycler.adapter = PlacesCollectionAdapter(arrayOf(PlacesCollectionModel("")))
        binding.cityListRecycler.adapter = PlacesCollectionAdapter(arrayOf(PlacesCollectionModel("")))
    }

    fun populateStateNames(stateNamesArray: Array<PlacesCollectionModel?>) {
        binding.stateListRecycler.swapAdapter(PlacesCollectionAdapter(stateNamesArray), true)
    }

    fun populateCityNames(cityNamesArray: Array<PlacesCollectionModel?>) {
        binding.cityListRecycler.swapAdapter(PlacesCollectionAdapter(cityNamesArray), true)
    }
}


