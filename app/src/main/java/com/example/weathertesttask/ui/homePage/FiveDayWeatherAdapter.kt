package com.example.weathertesttask.ui.homePage

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.weathertesttask.databinding.ItemDayWeatherBinding
import com.example.weathertesttask.domain.DayWeather

class FiveDayWeatherAdapter(
    val listDayWeather: List<DayWeather>,
    val onChooseDayClicked: () -> Unit
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

    override fun getItemCount() = listDayWeather.size

    private fun getDayWeather(position: Int): DayWeather {
        return listDayWeather[position]
    }

    inner class ViewHolder(val binding: ItemDayWeatherBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(dayWeather: DayWeather) {
            binding.dateValue.text = dayWeather.dtTxt
            binding.cloudsValue.text = dayWeather.clouds.toString()
            binding.humidityValue.text = dayWeather.main.humidity.toString()
            binding.pressureValue.text = dayWeather.main.pressure.toString()
            binding.minTemperatureValue.text = dayWeather.main.tempMin.toString()
            binding.maxTemperatureValue.text = dayWeather.main.tempMax.toString()
            binding.windValue.text = dayWeather.wind.toString()
        }
    }
}