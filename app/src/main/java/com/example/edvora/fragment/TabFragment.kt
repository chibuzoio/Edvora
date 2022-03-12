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


class TabFragment : Fragment() {
    var position = 0

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

//        textView = view.findViewById(R.id.textView) as TextView
//        textView.setText("Fragment " + (position + 1))

        // fetch the required data here for every given fragment
        // implement recyclerview here

        val ridesCollectionRecycler = view.findViewById<RecyclerView>(R.id.ridesCollectionRecycler)

        ridesCollectionRecycler.layoutManager = LinearLayoutManager(view.context, RecyclerView.VERTICAL, false)
        ridesCollectionRecycler.itemAnimator = DefaultItemAnimator()

        val ridesCollection = ArrayList<RidesCollectionModel>()

        if (position == 0) {
            // fetch Nearest

        }

        if (position == 1) {
            // fetch Upcoming

        }

        if (position == 2) {
            //fetch Past

        }

        ridesCollectionRecycler.adapter = RidesCollectionAdapter(ridesCollection)
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


