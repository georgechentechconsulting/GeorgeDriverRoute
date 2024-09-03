package com.example.driverroute

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.driverroute.api.ApiService
import com.example.driverroute.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: DriverViewModel by viewModels {
        DriverViewModelFactory(Repository(ApiService.create()))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapter = DriverAdapter { driver ->
            val intent = Intent(this, RouteActivity::class.java)
            intent.putExtra("DRIVER_ID", driver.id)
            startActivity(intent)
        }

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter

        viewModel.drivers.observe(this) { drivers ->
            adapter.submitList(drivers)
        }

        binding.sortButton.setOnClickListener {
            viewModel.sortDrivers()
        }
    }
}
