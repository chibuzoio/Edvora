package com.example.edvora.fragment

import android.app.ProgressDialog
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
import com.example.edvora.utility.Utility
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import okhttp3.*
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*
import java.util.regex.Pattern

class TabFragment : Fragment() {
    private var position: Int? = 0
    private var contextView: View? = null
    private lateinit var progressDialog: ProgressDialog
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

        progressDialog = ProgressDialog(activity)
        progressDialog.setMessage("Rides Are Loading...")
        progressDialog.show()

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
                var specificRidesCollectionArray = arrayOfNulls<RidesCollectionModel>(0)

                activity?.runOnUiThread {
                    val mapper = jacksonObjectMapper()
                    ridesCollectionArray = mapper.readValue(myResponse)

                    if (position == 0) {
                        for (ridesCollectionModel in ridesCollectionArray!!) {
                            if (ridesCollectionModel.station_path.contains(40)) {
                                specificRidesCollectionArray = Utility.append(specificRidesCollectionArray, ridesCollectionModel)
                            }
                        }
                    }

                    if (position == 1) {
                        for (ridesCollectionModel in ridesCollectionArray!!) {
                            val currentTime = System.currentTimeMillis()

                            if ((getDateTime(ridesCollectionModel) - currentTime) > 0) {
                                specificRidesCollectionArray = Utility.append(specificRidesCollectionArray, ridesCollectionModel)
                            }
                        }
                    }

                    if (position == 2) {
                        for (ridesCollectionModel in ridesCollectionArray!!) {
                            val currentTime = System.currentTimeMillis()

                            if ((getDateTime(ridesCollectionModel) - currentTime) < 0) {
                                specificRidesCollectionArray = Utility.append(specificRidesCollectionArray, ridesCollectionModel)
                            }
                        }
                    }

                    ridesCollectionRecycler.adapter = RidesCollectionAdapter(specificRidesCollectionArray)
                    progressDialog.hide()
                }
            }
        })
    }

    fun getDateTime(ridesCollectionModel: RidesCollectionModel): Long {
        val formatter = SimpleDateFormat("MM/dd/yyyy HH:mm aa", Locale.ENGLISH)
        val localDate = formatter.parse(ridesCollectionModel.date)

        return localDate!!.time
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


