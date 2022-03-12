package com.example.edvora.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.Nullable
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.edvora.R
import com.example.edvora.adapter.RidesCollectionAdapter
import com.example.edvora.model.RidesCollectionModel
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import okhttp3.*
import java.io.IOException
import java.util.regex.Pattern

class TabFragment : Fragment() {
    private var position: Int? = 0
    private var contextView: View? = null
    private lateinit var ridesCollectionRecycler: RecyclerView
    private var ridesCollectionArray: Array<RidesCollectionModel>? = null

    private val urlPattern: Pattern = Pattern.compile(
        "(?:^|[\\W])((ht|f)tp(s?):\\/\\/|www\\.)"
                + "(([\\w\\-]+\\.){1,}?([\\w\\-.~]+\\/?)*"
                + "[\\p{Alnum}.,%_=?&#\\-+()\\[\\]\\*$~@!:/{};']*)",
        Pattern.CASE_INSENSITIVE or Pattern.MULTILINE or Pattern.DOTALL
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        position = requireArguments().getInt("position")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_tab, container, false)
    }

    override fun onViewCreated(view: View, @Nullable savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        contextView = view
        ridesCollectionRecycler = view.findViewById(R.id.ridesCollectionRecycler)

        ridesCollectionRecycler.layoutManager = LinearLayoutManager(view.context, RecyclerView.VERTICAL, false)
        ridesCollectionRecycler.itemAnimator = DefaultItemAnimator()

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

                activity?.runOnUiThread {
                    val mapper = jacksonObjectMapper()
                    ridesCollectionArray = mapper.readValue(myResponse)

                    if (position == 0) {
                        // fetch Nearest

                    }

                    if (position == 1) {
                        // fetch Upcoming

                    }

                    if (position == 2) {
                        //fetch Past

                    }

                    ridesCollectionRecycler.adapter = RidesCollectionAdapter(ridesCollectionArray!!)
                }
            }
        })
    }

    companion object {
        fun getInstance(position: Int): Fragment {
            val bundle = Bundle()
            bundle.putInt("position", position)
            val tabFragment = TabFragment()
            tabFragment.arguments = bundle
            return tabFragment
        }
    }
}


