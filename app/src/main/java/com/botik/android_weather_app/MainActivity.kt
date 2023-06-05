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

        binding.temperature.text = "ожидайте ..."
        val weather = Weather()
        try {
            val response = weather.getWeather("Кривой Рог")
            binding.temperature.text = response.current?.temp_c.toString()
        } catch (e: Exception) {
            binding.temperature.text = "[погода] ошибка"
        }
    }
}