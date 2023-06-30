package com.botik.android_weather_app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import com.botik.android_weather_app.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.textTemperature.text = "ожидайте ..."
        try {
            val weather = Weather()
            val response = weather.getWeather("Кривой Рог")
            binding.textCountry.text = "Страна: " + response?.location?.country.toString()
            binding.textCity.text = "Город: " + response?.location?.name.toString()
            binding.textCondition.text = "Условия: " + response?.current?.condition?.text.toString()
            binding.textTemperature.text = "Температура C: " + response?.current?.temp_c.toString()
            binding.textFeelslike.text = "Чувствуется как: " + response?.current?.feelslike_c.toString()
            if((response?.current?.wind_kph as Int) < 4) {
                binding.textWind.text = "Скорость ветра км/ч: " + response?.current?.wind_kph.toString() + " (слабый ветер)"
            }
            else if((response?.current?.wind_kph as Int) < 8) {
                binding.textWind.text = "Скорость ветра км/ч: " + response?.current?.wind_kph.toString() + " (средний ветер)"
            }
            else {
                binding.textWind.text = "Скорость ветра км/ч: " + response?.current?.wind_kph.toString() + " (сильный ветер)"
            }
            binding.textCloud.text = "Облачность %: " + response?.current?.cloud.toString()
        } catch (e: Exception) {
            binding.textCountry.text = "[погода] ошибка"
        }

        binding.elevatedButtonToday.setOnClickListener() {
            setTheme(R.style.MyAppTheme)
            recreate()
        }

        binding.elevatedButtonTomorrow.setOnClickListener() {
            val intent = Intent(this, SecondActivity::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left)
        }

        binding.elevatedButtonAfterday.setOnClickListener() {
            // TODO
        }
    }
}