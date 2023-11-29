package com.example.weathertesttask.ui.homePage

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.weathertesttask.databinding.ItemDayWeatherBinding
import com.example.weathertesttask.domain.ModifiedWeatherEntity

class ForecastAdapter(
    val onChooseDayClicked: (dayWeather: ModifiedWeatherEntity) -> Unit
) : RecyclerView.Adapter<ForecastAdapter.ViewHolder>() {
    private val weatherList: MutableList<ModifiedWeatherEntity> = mutableListOf()
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

    override fun getItemCount() = weatherList.size

    fun setData(newData: List<ModifiedWeatherEntity>) {
        weatherList.clear()
        weatherList.addAll(newData)
        notifyDataSetChanged()
    }

    private fun getDayWeather(position: Int): ModifiedWeatherEntity {
        return weatherList[position]
    }

    inner class ViewHolder(val binding: ItemDayWeatherBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(dayWeather: ModifiedWeatherEntity) {
            binding.dateValue.text = dayWeather.dtTxt
            binding.cloudsValue.text = dayWeather.clouds.toString()
            binding.humidityValue.text = dayWeather.humidity.toString()
            binding.pressureValue.text = dayWeather.pressure.toString()
            binding.minTemperatureValue.text = dayWeather.tempMin.toString()
            binding.maxTemperatureValue.text = dayWeather.tempMax.toString()
            binding.windValue.text = dayWeather.wind.toString()
            binding.dayWeatherContainer.setOnClickListener {
                onChooseDayClicked(dayWeather)
            }
        }
    }
}