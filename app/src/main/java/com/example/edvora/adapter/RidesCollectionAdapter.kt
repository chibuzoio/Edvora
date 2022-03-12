package com.example.edvora.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.FitCenter
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.example.edvora.R
import com.example.edvora.databinding.RecyclerRidesCollectionBinding
import com.example.edvora.model.RidesCollectionModel

class RidesCollectionAdapter(private val ridesCollection: ArrayList<RidesCollectionModel>) :
    RecyclerView.Adapter<RidesCollectionAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = RecyclerRidesCollectionBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        Glide.with(holder.itemView)
            .load(ridesCollection[position].mapUrl)
            .transform(FitCenter(), RoundedCorners(5))
            .into(holder.binding.locationImage)

        holder.binding.cityTextView.text = ridesCollection[position].city
        holder.binding.stateTextView.text = ridesCollection[position].state
        holder.binding.rideIdTextView.text = holder.itemView.context.getString(R.string.ride_id).plus(ridesCollection[position].id.toString())
        holder.binding.originStationTextView.text = holder.itemView.context.getString(R.string.origin_station).plus(ridesCollection[position].originStationCode.toString())
        holder.binding.stationPathTextView.text = holder.itemView.context.getString(R.string.station_path).plus(ridesCollection[position].stationPath.toString())
        holder.binding.dateTextView.text = holder.itemView.context.getString(R.string.date).plus(ridesCollection[position].date)
        holder.binding.distanceTextView.text = holder.itemView.context.getString(R.string.distance).plus(0.toString())
    }

    override fun getItemCount(): Int {
        return ridesCollection.size
    }

    class MyViewHolder(val binding: RecyclerRidesCollectionBinding) :
        RecyclerView.ViewHolder(binding.root)
}


