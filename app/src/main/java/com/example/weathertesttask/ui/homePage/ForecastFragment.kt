package com.example.weathertesttask.ui.homePage

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
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.weathertesttask.R
import com.example.weathertesttask.databinding.FragmentFiveDayWeatherBinding
import com.example.weathertesttask.domain.ModifiedWeatherEntity
import org.koin.androidx.viewmodel.ext.android.viewModel

class ForecastFragment : Fragment() {
    private var _binding: FragmentFiveDayWeatherBinding? = null
    private val binding get() = _binding!!
    private val weatherViewModel by viewModel<ForecastViewModel>()
    private lateinit var forecastAdapter: ForecastAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFiveDayWeatherBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        forecastAdapter = ForecastAdapter { dayWeather: ModifiedWeatherEntity ->
            onChooseDayClicked(dayWeather)
        }
        binding.forecastRecycler.adapter = forecastAdapter
        binding.forecastRecycler.layoutManager = LinearLayoutManager(requireContext())
        weatherViewModel.noData.observe(viewLifecycleOwner) {
            binding.noDataTextView.text = it
        }

        weatherViewModel.fiveDaysForecast.observe(viewLifecycleOwner) { forecast ->
            binding.apply {
                if (forecast != null) {
                    forecastAdapter.setData(forecast)
                }
            }
        }

        weatherViewModel.isLoading.observe(viewLifecycleOwner) { isLoading ->
            binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
        }

        checkLocationPermission()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
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
            requestPermission.launch(ACCESS_FINE_LOCATION)
        }
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