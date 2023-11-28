package com.example.weathertesttask.ui.homePage

import android.Manifest
import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.content.ContentValues.TAG
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.constraintlayout.motion.widget.Debug.getLocation
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.weathertesttask.Config.Companion.REQUEST_CODE_LOCATION
import com.example.weathertesttask.R
import com.example.weathertesttask.databinding.FragmentFiveDayWeatherBinding
import com.example.weathertesttask.domain.ModifiedWeatherEntity
import org.koin.androidx.viewmodel.ext.android.viewModel

class FiveDayWeatherFragment : Fragment() {
    private lateinit var binding: FragmentFiveDayWeatherBinding
    private val weatherViewModel by viewModel<FiveDaysWeatherViewModel>()
    private lateinit var fiveDayWeatherAdapter: FiveDayWeatherAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFiveDayWeatherBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        checkLocationPermission()
        weatherViewModel.noData.observe(viewLifecycleOwner) {
            binding.noDataTextView.text = it
        }

        weatherViewModel.fiveDaysForecast.observe(viewLifecycleOwner) { weatherResponse ->
            binding.apply {
                if (weatherResponse != null) {
                    fiveDayWeatherAdapter =
                        FiveDayWeatherAdapter(weatherResponse) { dayWeather: ModifiedWeatherEntity ->
                            onChooseDayClicked(dayWeather)
                        }
                    binding.forecastRecycler.adapter = fiveDayWeatherAdapter
                    binding.forecastRecycler.layoutManager = LinearLayoutManager(requireContext())
                }
            }
        }
    }

    private fun onChooseDayClicked(dayWeather: ModifiedWeatherEntity) {
        val bundle = Bundle().apply {
            putSerializable("dayWeather", dayWeather)
        }
        findNavController().navigate(R.id.action_fiveDayWeatherFragment_to_detailsFragment, bundle)
    }

    private fun checkLocationPermission() {
        if (ContextCompat.checkSelfPermission(
                requireContext(),
                ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            weatherViewModel.initViewModel(requireContext())
        } else {
            requestLocationPermission()
        }
    }

    private fun requestLocationPermission() {
        ActivityCompat.requestPermissions(
            requireActivity(),
            arrayOf(ACCESS_FINE_LOCATION),
            REQUEST_CODE_LOCATION
        )
        requestPermission.launch(ACCESS_FINE_LOCATION)
    }

    private val requestPermission =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { permission ->
            if (permission == true) {
                weatherViewModel.initViewModel(requireContext())
            } else {
                Log.d(TAG, "Permission not granted")
            }
        }
}