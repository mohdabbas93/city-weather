package com.mohdabbas.cityweather.ui.list.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mohdabbas.cityweather.R
import com.mohdabbas.cityweather.data.CityWeather
import com.mohdabbas.cityweather.databinding.ItemCityWeatherBinding

/**
 * Created by Mohammad Abbas
 * On: 5/14/22.
 */
class CityWeatherAdapter(private var data: List<CityWeather>) :
    RecyclerView.Adapter<CityWeatherAdapter.ViewHolder>() {

    class ViewHolder(binding: ItemCityWeatherBinding) :
        RecyclerView.ViewHolder(binding.root) {

        val view = binding.root

        val tvTemp: TextView = binding.tvTemp
        val tvCityCountryName: TextView = binding.tvCityCountryName
        val tvWeatherCondition: TextView = binding.tvWeatherCondition
        val ivWeatherConditionIcon: ImageView = binding.ivWeatherIcon
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemCityWeatherBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.apply {
            tvTemp.text = data[position].main.temp.toInt().toString()
            tvCityCountryName.text = view.context.getString(
                R.string.city_name_country_name,
                data[position].city.name,
                data[position].city.country
            )
            tvWeatherCondition.text = data[position].weather.firstOrNull()?.main ?: ""
        }
    }

    override fun getItemCount() = data.size

    fun updateData(newData: List<CityWeather>) {
        data = newData
        notifyDataSetChanged()
    }
}