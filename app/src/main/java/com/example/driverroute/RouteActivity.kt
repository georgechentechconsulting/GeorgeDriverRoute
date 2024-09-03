package com.example.driverroute

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.example.driverroute.api.ApiService
import com.example.driverroute.databinding.ActivityRouteBinding

class RouteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRouteBinding
    private val viewModel: DriverViewModel by viewModels {
        DriverViewModelFactory(Repository(ApiService.create()))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRouteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // back button
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        // Get driver
        val driverId = intent.getIntExtra("DRIVER_ID", -1)

        viewModel.routes.observe(this) { routes ->
            val route = viewModel.getRouteForDriver(driverId)
            if (route != null) {
                binding.routeType.text = "Route Type: ${route.type}"
                binding.routeName.text = "Route Name: ${route.name}"
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
