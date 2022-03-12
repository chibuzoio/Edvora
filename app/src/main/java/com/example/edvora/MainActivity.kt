package com.example.edvora

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.beust.klaxon.JsonReader
import com.beust.klaxon.Klaxon
import com.example.edvora.adapter.ViewPagerAdapter
import com.example.edvora.databinding.ActivityMainBinding
import com.example.edvora.model.RidesCollectionModel
import okhttp3.*
import java.io.IOException
import java.io.StringReader

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapter = ViewPagerAdapter(supportFragmentManager)
        binding.viewPager.adapter = adapter

        binding.tabLayout.setupWithViewPager(binding.viewPager)

        try {
            fetchData()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    @Throws(IOException::class)
    fun fetchData() {
        val client = OkHttpClient()
        val request: Request = Request.Builder()
            .url(getString(R.string.project_api))
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException?) {
                call.cancel()
            }

            @Throws(IOException::class)
            override fun onResponse(call: Call?, response: Response) {
                val myResponse: String = response.body()!!.string()
                runOnUiThread {
                    val klaxon = Klaxon()
                    val ridesCollectionArray = ArrayList<RidesCollectionModel>()

                    JsonReader(StringReader(myResponse)).use { reader ->
                        reader.beginArray {
                            while (reader.hasNext()) {
                                val ridesCollectionModel = klaxon.parse<RidesCollectionModel>(reader)
                                ridesCollectionArray.add(ridesCollectionModel!!)
                            }
                        }
                    }
                }
            }
        })
    }
}


