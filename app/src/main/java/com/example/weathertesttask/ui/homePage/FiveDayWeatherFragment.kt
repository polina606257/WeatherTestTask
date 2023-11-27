package com.example.weathertesttask.ui.homePage

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.weathertesttask.R
import com.example.weathertesttask.databinding.FragmentFiveDayWeatherBinding
import com.example.weathertesttask.domain.HourlyDayWeather
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import org.koin.androidx.viewmodel.ext.android.viewModel

class FiveDayWeatherFragment : Fragment() {
    private lateinit var binding: FragmentFiveDayWeatherBinding
    private val weatherViewModel by viewModel<FiveDaysWeatherViewModel>()
    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient
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
        fusedLocationProviderClient =
            LocationServices.getFusedLocationProviderClient(requireActivity())
        weatherViewModel.findLocation(
            fusedLocationProviderClient,
            requireContext(),
            onLocationFound = {
                weatherViewModel.initViewModel()
            },
            askForPermission = { askForPermission() }
        )

        weatherViewModel.fiveDaysForecast.observe(viewLifecycleOwner) { weatherResponse ->
            binding.apply {
                if (weatherResponse != null) {
                    fiveDayWeatherAdapter =
                        FiveDayWeatherAdapter(weatherResponse.list) { hourlyDayWeather: HourlyDayWeather ->
                            onChooseDayClicked(hourlyDayWeather)
                        }
                    binding.forecastRecycler.adapter = fiveDayWeatherAdapter
                    binding.forecastRecycler.layoutManager = LinearLayoutManager(requireContext())
                }
            }
        }
    }

    private fun onChooseDayClicked(hourlyDayWeather: HourlyDayWeather) {
            val bundle = Bundle().apply {
                putSerializable("dayWeather", hourlyDayWeather)
            }
              findNavController().navigate(R.id.action_fiveDayWeatherFragment_to_detailsFragment, bundle)
    }

    private fun askForPermission() {
        ActivityCompat.requestPermissions(
            requireActivity(),
            arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
            weatherViewModel.REQUEST_CODE
        )
    }

    @Deprecated("Deprecated in Java")
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        when (requestCode) {
            weatherViewModel.REQUEST_CODE -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    weatherViewModel.findLocation(
                        fusedLocationProviderClient,
                        requireContext(),
                        onLocationFound = {

                        }) { askForPermission() }
                } else {
                    Log.d("TAG", "Permission denied")
                }
            }
        }
    }
}