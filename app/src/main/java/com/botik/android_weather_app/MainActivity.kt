package com.botik.android_weather_app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.botik.android_weather_app.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var response: WeatherResponse
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val weather = Weather()
        response = weather.getWeather("Кривой Рог")!!
        initRecyclerView()

//        binding.textTemperature.text = "ожидайте ..."
//        try {
//            val weather = Weather()
//            val response = weather.getWeather("Кривой Рог")
//            binding.textCountry.text = "Страна: ${response?.location?.country}"
//            binding.textCity.text = "Город: ${response?.location?.name}"
//            binding.textCondition.text = "Условия: ${response?.current?.condition?.text}"
//            binding.textTemperature.text = "Температура C: ${response?.current?.temp_c}"
//            binding.textFeelslike.text = "Чувствуется как: ${response?.current?.feelslike_c}"
//            if(response?.current?.wind_kph!! < 4) {
//                binding.textWind.text = "Скорость ветра км/ч: ${response?.current?.wind_kph} (слабый ветер)"
//            }
//            else if(response?.current?.wind_kph!! < 8) {
//                binding.textWind.text = "Скорость ветра км/ч: ${response?.current?.wind_kph} (средний ветер)"
//            }
//            else {
//                binding.textWind.text = "Скорость ветра км/ч: ${response?.current?.wind_kph} (сильный ветер)"
//            }
//            binding.textCloud.text = "Облачность %: ${response?.current?.cloud}"
//        } catch (e: Exception) {
//            binding.textCountry.text = "[погода] ошибка"
//        }

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

    private fun initRecyclerView() {
        val recyclerview = binding.recyclerView
        val adapter = AdapterForRecyclerView()
        recyclerview.adapter = adapter
        adapter.setList(response.forecast.forecastday[0].hour)
    }
}