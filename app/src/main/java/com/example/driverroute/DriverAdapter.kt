package com.example.driverroute

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.driverroute.databinding.ItemDriverBinding


class DriverAdapter(private val onItemClick: (Driver) -> Unit) :
    ListAdapter<Driver, DriverAdapter.DriverViewHolder>(DriverDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DriverViewHolder {
        val binding = ItemDriverBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DriverViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DriverViewHolder, position: Int) {
        val driver = getItem(position)
        holder.bind(driver, onItemClick)
    }

    class DriverViewHolder(private val binding: ItemDriverBinding) :
        RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("SetTextI18n")
        fun bind(driver: Driver, onItemClick: (Driver) -> Unit) {
            binding.driverName.text = driver.name
            binding.driverId.text = driver.id.toString()
            binding.root.setOnClickListener { onItemClick(driver) }
        }
    }
}