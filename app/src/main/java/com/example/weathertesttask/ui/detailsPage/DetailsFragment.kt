package com.example.weathertesttask.ui.detailsPage

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.weathertesttask.databinding.FragmentDetailsBinding
import com.example.weathertesttask.domain.HourlyDayWeather

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
        val hourlyDayWeather = arguments?.getSerializable("dayWeather") as HourlyDayWeather
        binding.dateValue.text = hourlyDayWeather.dtTxt
        binding.cloudsValue.text = hourlyDayWeather.clouds.all.toString()
        binding.humidityValue.text = hourlyDayWeather.main.humidity.toString()
        binding.pressureValue.text = hourlyDayWeather.main.pressure.toString()
        binding.minTemperatureValue.text = hourlyDayWeather.main.tempMin.toString()
        binding.maxTemperatureValue.text = hourlyDayWeather.main.tempMax.toString()
        binding.windValue.text = hourlyDayWeather.wind.speed.toString()
        binding.feelsLike.text = hourlyDayWeather.main.feelsLike.toString()
        binding.popValue.text = hourlyDayWeather.pop.toString()
        binding.sysValue.text = hourlyDayWeather.sys.pod

    }
}