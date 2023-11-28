package com.example.weathertesttask.ui.homePage

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Geocoder
import android.location.Location
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weathertesttask.data.DataResult
import com.example.weathertesttask.data.remote.ConnectionDetector
import com.example.weathertesttask.domain.ModifiedWeatherEntity
import com.example.weathertesttask.ui.usecases.GetFiveDaysForecastUseCase
import com.example.weathertesttask.ui.usecases.GetForecastFromDatabaseUseCase
import com.example.weathertesttask.ui.usecases.SaveDayWeatherToDatabaseUseCase
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.tasks.OnSuccessListener
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.Locale

class FiveDaysWeatherViewModel(
    val getFiveDaysForecastUseCase: GetFiveDaysForecastUseCase,
    val getForecastFromDatabaseUseCase: GetForecastFromDatabaseUseCase,
    val saveDayWeatherToDatabaseUseCase: SaveDayWeatherToDatabaseUseCase,
    val connectionDetector: ConnectionDetector
) : ViewModel() {
    lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    private val _fiveDaysForecast = MutableLiveData<List<ModifiedWeatherEntity>?>()
    val fiveDaysForecast: MutableLiveData<List<ModifiedWeatherEntity>?> = _fiveDaysForecast
    private val _noData = MutableLiveData("")
    val noData: LiveData<String> = _noData

    private var latitude: Double? = null
    private var longitude: Double? = null

    fun initViewModel(context: Context) {
        viewModelScope.launch {
            when (connectionDetector.isConnectingToInternet()) {
                true -> {
                    fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(context)
                    if (ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION)
                        == PackageManager.PERMISSION_GRANTED
                    ) {
                        fusedLocationProviderClient.lastLocation
                            .addOnSuccessListener(OnSuccessListener { location ->
                                getLanAndLon(context, location, onLocationFound = {
                                    getAndSaveApiData()
                                })
                            })
                    }
                }

                else -> {
                    when (val dataResult =
                        withContext(Dispatchers.IO) { getForecastFromDatabaseUseCase() }) {
                        is DataResult.Success -> {
                            if (dataResult.response.isEmpty()) {
                                _noData.value = "You need to connect to internet to get first data"
                            }
                            _fiveDaysForecast.value = dataResult.response
                        }

                        is DataResult.Error -> {
                            Log.i("TAG", "Couldn't find any weather, error ${dataResult.error}")
                        }
                    }
                }
            }
        }
    }

    private fun getAndSaveApiData() {
        viewModelScope.launch {
            if (latitude != null && longitude != null) {
                when (val dataResult =
                    getFiveDaysForecastUseCase(latitude!!, longitude!!)) {
                    is DataResult.Success -> {
                        _fiveDaysForecast.value = dataResult.response
                        for (dayWeather in fiveDaysForecast.value!!) {
                            withContext(Dispatchers.IO) {
                                saveDayWeatherToDatabaseUseCase(dayWeather)
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

    private fun getLanAndLon(
        context: Context,
        location: Location,
        onLocationFound: () -> Unit
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

//    private fun askForPermission(activity: FragmentActivity, context: Context) {
//        ActivityCompat.requestPermissions(
//            activity,
//            arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
//            REQUEST_CODE
//        )
//    }
}
