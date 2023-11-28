package com.example.weathertesttask.ui.detailsPage

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.weathertesttask.databinding.FragmentDetailsBinding
import com.example.weathertesttask.domain.ModifiedWeatherEntity

class DetailsFragment : Fragment() {
    private lateinit var binding: FragmentDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailsBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val dayWeather = arguments?.getSerializable("dayWeather") as ModifiedWeatherEntity
        bindFields(dayWeather)
    }
    private fun bindFields(dayWeather: ModifiedWeatherEntity) {
        binding.dateValue.text = dayWeather.dtTxt
        binding.cloudsValue.text = dayWeather.clouds.toString()
        binding.humidityValue.text = dayWeather.humidity.toString()
        binding.pressureValue.text = dayWeather.pressure.toString()
        binding.minTemperatureValue.text = dayWeather.tempMin.toString()
        binding.maxTemperatureValue.text = dayWeather.tempMax.toString()
        binding.windValue.text = dayWeather.wind.toString()
        binding.feelsLike.text = dayWeather.feelsLike.toString()
        binding.popValue.text = dayWeather.pop.toString()
        binding.sysValue.text = dayWeather.sys
    }
}