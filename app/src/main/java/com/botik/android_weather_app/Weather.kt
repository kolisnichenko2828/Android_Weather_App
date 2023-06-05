package com.botik.android_weather_app

import kotlinx.coroutines.*
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*
import java.util.concurrent.TimeUnit

class Weather {
    private val API_KEY: String = "01146fdd46a844f7a6793903230206"
    fun getWeather(city: String): WeatherResponse {
        val okHttpClient = OkHttpClient.Builder()
            .callTimeout(2, TimeUnit.MINUTES)
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl("http://api.weatherapi.com/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()

        val service = retrofit.create(WeatherAPI::class.java)

        var response: WeatherResponse
        runBlocking {
            response = service.getCurrentWeather(API_KEY, city, "no", "ru")
        }
        return response
    }
}

interface WeatherAPI {
    @GET("current.json?")
    suspend fun getCurrentWeather(@Query("key") key: String,
                                  @Query("q") q: String,
                                  @Query("aqi") aqi: String,
                                  @Query("lang") lang: String): WeatherResponse
}

data class WeatherResponse(
    val location: Location?,
    val current: Current?
)

data class Location(
    val name: String?,
    val region: String?,
    val country: String?,
    val lat: Double,
    val lon: Double,
    val tz_id: String?,
    val localtime_epoch: Long,
    val localtime: String?,
)

data class Current(
    val last_updated_epoch: Long,
    val last_updated: String?,
    val temp_c: Double,
    val temp_f: Double,
    val is_day: Double,
    val condition: Condition?,
    val wind_mph: Double,
    val wind_kph: Double,
    val wind_degree: Int,
    val wind_dir: String?,
    val pressure_mb: Double,
    val pressure_in: Double,
    val precip_mm: Double,
    val precip_in: Double,
    val humidity: Double,
    val cloud: Double,
    val feelslike_c: Double,
    val feelslike_f: Double,
    val vis_km: Double,
    val vis_miles: Double,
    val uv: Double,
    val gust_mph: Double,
    val gust_kph: Double
)

data class Condition(
    val text: String?,
    val icon: String?,
    val code: Int
)