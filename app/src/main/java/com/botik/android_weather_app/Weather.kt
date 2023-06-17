package com.botik.android_weather_app

import kotlinx.coroutines.*
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*
import java.util.concurrent.TimeUnit

class Weather() {
    private var API_KEY: String = ""
    private var okHttpClient: OkHttpClient? = null
    private var retrofit: Retrofit? = null
    private var service: WeatherAPI? = null
    private var response: WeatherResponse? = null

    init {
        API_KEY = "01146fdd46a844f7a6793903230206"
        okHttpClient = OkHttpClient.Builder()
            .callTimeout(2, TimeUnit.MINUTES)
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .build()
        retrofit = Retrofit.Builder()
            .baseUrl("http://api.weatherapi.com/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient as OkHttpClient)
            .build()
        service = retrofit?.create(WeatherAPI::class.java)
    }
    fun getCurrentWeather(city: String): WeatherResponse? {
        runBlocking {
            response = service?.getCurrentWeather(API_KEY, city, "no", "ru")
        }
        return response
    }
    fun getDayWeather(city: String, days: Int = 1): WeatherResponse? {
        runBlocking {
            response = service?.getDayWeather(API_KEY, city, "no", "ru", days)
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

    @GET("forecast.json?")
    suspend fun getDayWeather(@Query("key") key: String,
                              @Query("q") q: String,
                              @Query("aqi") aqi: String,
                              @Query("lang") lang: String,
                              @Query("days") days: Int): WeatherResponse
}

data class WeatherResponse(
    val location: Location?,
    val current: Current?,
    val forecast: Forecast?
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

// forecast

data class Forecast(
    val forecastday: List<ForecastDay>
)

data class ForecastDay(
    val date: String,
    val date_epoch: Double,
    val day: Day,
    // val astro: Astro,
    // val hour: List<Hour>
)

data class Day(
    val maxtemp_c: Double,
    val maxtemp_f: Double,
    val mintemp_c: Double,
    val mintemp_f: Double,
    val avgtemp_c: Double,
    val avgtemp_f: Double,
    val maxwind_mph: Double,
    val maxwind_kph: Double,
    val totalprecip_mm: Double,
    val totalprecip_in: Double,
    val totalsnow_cm: Double,
    val avgvis_km: Double,
    val avgvis_miles: Double,
    val avghumidity: Double,
    val daily_will_it_rain: Double,
    val daily_chance_of_rain: String,
    val daily_will_it_snow: Double,
    val daily_chance_of_snow: String,
    val condition: Condition,
    val uv: Double
)