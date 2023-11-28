package com.example.weathertesttask.ui.homePage

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Geocoder
import android.location.Location
import android.util.Log
import androidx.core.content.ContextCompat
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weathertesttask.data.DataResult
import com.example.weathertesttask.domain.WeatherEntityForRoom
import com.example.weathertesttask.domain.WeatherResponse
import com.example.weathertesttask.ui.usecases.GetFiveDaysForecastUseCase
import com.example.weathertesttask.ui.usecases.GetForecastFromDatabaseUseCase
import com.example.weathertesttask.ui.usecases.SaveDayWeatherToDatabaseUseCase
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.tasks.OnSuccessListener
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.Locale


class FiveDaysWeatherViewModel(
    val getFiveDaysForecastUseCase: GetFiveDaysForecastUseCase,
    val getForecastFromDatabaseUseCase: GetForecastFromDatabaseUseCase,
    val saveDayWeatherToDatabaseUseCase: SaveDayWeatherToDatabaseUseCase
) : ViewModel() {
    private val _fiveDaysForecast = MutableLiveData<WeatherResponse?>()
    val fiveDaysForecast: LiveData<WeatherResponse?> = _fiveDaysForecast

    val REQUEST_CODE = 100
    private var latitude: Double? = null
    private var longitude: Double? = null

    fun initViewModel() {
        viewModelScope.launch {
            if (latitude != null && longitude != null) {
                when (val dataResult = getFiveDaysForecastUseCase(latitude!!, longitude!!)) {
                    is DataResult.Success -> {
                        _fiveDaysForecast.value = dataResult.response
                        for (dayWeather in fiveDaysForecast.value!!.list) {
                            val dayWeatherForSaving = WeatherEntityForRoom(
                                dayWeather.dt,
                                dayWeather.dtTxt,
                                dayWeather.clouds.all,
                                dayWeather.main.humidity,
                                dayWeather.main.pressure,
                                dayWeather.main.tempMin,
                                dayWeather.main.tempMax,
                                dayWeather.wind.speed,
                                dayWeather.main.feelsLike,
                                dayWeather.pop,
                                dayWeather.sys.pod
                            )
                            withContext(Dispatchers.IO) {
                                saveDayWeatherToDatabaseUseCase(dayWeatherForSaving)
                            }
                        }
                    }

                    is DataResult.Error -> Log.i(
                        "TAG", "Couldn't find any weather, error ${dataResult.error}"
                    )
                }
            }
        }
    }

    fun findLocation(
        fusedLocationProviderClient: FusedLocationProviderClient,
        context: Context,
        onLocationFound: () -> Unit,
        askForPermission: () -> Unit
    ) {
        if (ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            fusedLocationProviderClient.lastLocation
                .addOnSuccessListener(OnSuccessListener { location ->
                    getLanAndLon(context, onLocationFound, location)
                })
        } else {
            askForPermission()
        }
    }

    private fun getLanAndLon(
        context: Context,
        onLocationFound: () -> Unit,
        location: Location
    ) {
        try {
            val geocoder = Geocoder(context, Locale.getDefault())
            val address =
                geocoder.getFromLocation(location.latitude, location.longitude, 1)
            latitude = address?.get(0)?.latitude
            longitude = address?.get(0)?.longitude
            onLocationFound.invoke()
        } catch (e: Exception) {
            Log.d("TAG", "Couldn't get location")
        }
    }
}