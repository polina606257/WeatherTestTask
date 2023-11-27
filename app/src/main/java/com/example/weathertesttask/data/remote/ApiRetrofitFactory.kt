package com.example.weathertesttask.data.remote

import com.example.weathertesttask.BuildConfig
import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiRetrofitFactory {

    fun provideRetrofit(): Retrofit {
        val authInterceptor = Interceptor { chain ->
            var request: Request = chain.request()
            val url: HttpUrl =
                request.url().newBuilder().addQueryParameter("appid", BuildConfig.API_KEY).build()
            request = request.newBuilder().url(url).build()
            chain.proceed(request)
        }

        val httpClient = OkHttpClient().newBuilder()
            .addInterceptor(authInterceptor)
            .build()

        return Retrofit.Builder()
            .client(httpClient)
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun provideApiService(retrofit: Retrofit): WeatherApiService =
        retrofit.create(WeatherApiService::class.java)
}
