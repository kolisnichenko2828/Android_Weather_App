package com.botik.android_weather_app

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.botik.android_weather_app.databinding.ActivityMainBinding
import com.botik.android_weather_app.databinding.LayoutForItemInRecyclerViewBinding

// нужен чтобы связать Model и RecyclerView
class AdapterForRecyclerView: RecyclerView.Adapter<AdapterForRecyclerView.ViewHolderForRecyclerView>() {
    private lateinit var binding: LayoutForItemInRecyclerViewBinding
    private var listHours = emptyList<Hour>()

    // нужен в onBindViewHolder, он содержит объекты каждого элемента в RecyclerView
    class ViewHolderForRecyclerView(val binding: LayoutForItemInRecyclerViewBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderForRecyclerView {
        // здесь мы говорим RecyclerView ЧТО он будет показывать
        val inflater = LayoutInflater.from(parent.context)
        val binding = LayoutForItemInRecyclerViewBinding.inflate(inflater, parent, false)
        return ViewHolderForRecyclerView(binding)
    }

    override fun getItemCount(): Int {
        return listHours.size // указываем сколько всего элементов будет отображаться
    }

    override fun onBindViewHolder(holder: ViewHolderForRecyclerView, position: Int) {
        // здесь мы для каждого отдельного пункта устанавливаем свои значения
        with(holder.binding) {
            tvTime.text = "Время: ${listHours[position].time}"
            tvAvgtempC.text = "Средняя температура: ${listHours[position].temp_c}"
        }
    }

    fun setList(list: List<Hour>) {
        listHours = list
        notifyDataSetChanged()
    }
}