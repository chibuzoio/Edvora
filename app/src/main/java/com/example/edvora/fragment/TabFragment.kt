package com.example.edvora.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.Nullable
import androidx.fragment.app.Fragment
import com.example.edvora.R

class TabFragment : Fragment() {
    var position = 0
    private var textView: TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        position = requireArguments().getInt("pos")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_tab, container, false)
    }

    override fun onViewCreated(view: View, @Nullable savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        textView = view.findViewById<View>(R.id.textView) as TextView
        textView!!.text = "Fragment " + (position + 1)


        // implement recyclerview here
    }

    companion object {
        fun getInstance(position: Int): Fragment {
            val bundle = Bundle()
            bundle.putInt("pos", position)
            val tabFragment = TabFragment()
            tabFragment.arguments = bundle
            return tabFragment
        }
    }
}


