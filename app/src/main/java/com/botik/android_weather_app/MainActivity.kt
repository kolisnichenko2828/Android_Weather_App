package com.botik.android_weather_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.botik.android_weather_app.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.textTemperature.text = "ожидайте ..."
        val weather = Weather()
        try {
            val response = weather.getDayWeather("Кривой Рог")
            binding.textCountry.text = "Страна: " + response?.location?.country.toString()
            binding.textCity.text = "Город: " + response?.location?.name.toString()
            binding.textCondition.text = "Условия: " + response?.current?.condition?.text.toString()
            binding.textTemperature.text = "Температура C: " + response?.current?.temp_c.toString()
            binding.textFeelslike.text = "Чувствуется как: " + response?.current?.feelslike_c.toString()
            binding.textWindKmH.text = "Скорость ветра км/ч: " + response?.current?.wind_kph.toString()
            binding.textCloud.text = "Облачность %: " + response?.current?.cloud.toString()
        } catch (e: Exception) {
            binding.textCountry.text = "[погода] ошибка"
        }
    }
}