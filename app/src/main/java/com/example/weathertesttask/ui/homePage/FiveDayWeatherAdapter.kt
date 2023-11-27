package com.example.weathertesttask.ui.homePage

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.weathertesttask.databinding.ItemDayWeatherBinding
import com.example.weathertesttask.domain.HourlyDayWeather

class FiveDayWeatherAdapter(
    val listHourlyDayWeather: List<HourlyDayWeather>,
    val onChooseDayClicked: (hourlyDayWeather: HourlyDayWeather) -> Unit
) : RecyclerView.Adapter<FiveDayWeatherAdapter.ViewHolder>() {
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemDayWeatherBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        getDayWeather(position).also {
            viewHolder.bind(it)
        }
    }

    override fun getItemCount() = listHourlyDayWeather.size

    private fun getDayWeather(position: Int): HourlyDayWeather {
        return listHourlyDayWeather[position]
    }

    inner class ViewHolder(val binding: ItemDayWeatherBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(hourlyDayWeather: HourlyDayWeather) {
            binding.dateValue.text = hourlyDayWeather.dtTxt
            binding.cloudsValue.text = hourlyDayWeather.clouds.all.toString()
            binding.humidityValue.text = hourlyDayWeather.main.humidity.toString()
            binding.pressureValue.text = hourlyDayWeather.main.pressure.toString()
            binding.minTemperatureValue.text = hourlyDayWeather.main.tempMin.toString()
            binding.maxTemperatureValue.text = hourlyDayWeather.main.tempMax.toString()
            binding.windValue.text = hourlyDayWeather.wind.speed.toString()
            binding.dayWeatherContainer.setOnClickListener {
                onChooseDayClicked(hourlyDayWeather)
            }
        }
    }
}