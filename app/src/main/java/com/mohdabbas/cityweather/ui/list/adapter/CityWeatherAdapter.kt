package com.mohdabbas.cityweather.ui.list.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mohdabbas.cityweather.R
import com.mohdabbas.cityweather.data.CityWeather
import com.mohdabbas.cityweather.databinding.ItemCityWeatherBinding
import com.mohdabbas.cityweather.ui.list.CityWeatherListFragmentDirections
import com.mohdabbas.cityweather.util.WeatherUtil.temperatureFromKelvinToCelsius

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
            tvTemp.text = data[position].main.temp.temperatureFromKelvinToCelsius().toString()
            tvCityCountryName.text = view.context.getString(
                R.string.city_name_country_name,
                data[position].city.name,
                data[position].city.country
            )

            data[position].weather.firstOrNull()?.let {
                tvWeatherCondition.text = it.main ?: ""

                Glide.with(holder.view.context)
                    .load("http://openweathermap.org/img/wn/${it.icon ?: ""}@2x.png")
                    .centerCrop()
                    .placeholder(R.drawable.ic_launcher_background)
                    .into(holder.ivWeatherConditionIcon)
            }

            view.setOnClickListener {
                it.findNavController()
                    .navigate(
                        CityWeatherListFragmentDirections.actionCityWeatherListFragmentToCityWeatherDetailsFragment(
                            cityId = data[position].city.id
                        )
                    )
            }
        }
    }

    override fun getItemCount() = data.size

    fun updateData(newData: List<CityWeather>) {
        data = newData
        notifyDataSetChanged()
    }
}
/*

    private var _binding: FragmentCityWeatherDetailsBinding? = null
    private val binding get() = _binding!!

    private val sharedViewModel: SharedViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCityWeatherDetailsBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sharedViewModel

        val cityWeather = sharedViewModel.getCityWeatherInfoByCityId(
            sharedViewModel.citiesWeather.value?.first()?.city?.id ?: 1
        )
        binding.apply {
            cityWeather?.let {
                tvWeatherCondition.text =
                    it.weather.firstOrNull()?.main ?: "No weather condition available"
                tvTemp.text = it.main.temp.temperatureFromKelvinToCelsius().toString()
                Glide.with(requireContext())
                    .load("http://openweathermap.org/img/wn/${it.weather.firstOrNull()?.icon ?: ""}@2x.png")
                    .centerCrop()
                    .placeholder(R.drawable.ic_launcher_background)
                    .into(ivWeatherIcon)

                wind.tvTitle.text = "Wind"
                wind.tvValue.text = it.wind.speed.toInt().toString()

                humidity.tvTitle.text = "Humidity"
                humidity.tvValue.text = it.main.humidity.toInt().toString()

                pressure.tvTitle.text = "Pressure"
                pressure.tvValue.text = it.main.pressure.toInt().toString()
            }
        }
    }

    private fun inflateViews() {

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
 */