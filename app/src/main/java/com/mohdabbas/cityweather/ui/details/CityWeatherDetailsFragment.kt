package com.mohdabbas.cityweather.ui.details

//import com.mohdabbas.cityweather.ui.list.CityWeatherListFragmentArgs
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.mohdabbas.cityweather.R
import com.mohdabbas.cityweather.data.CityWeather
import com.mohdabbas.cityweather.databinding.FragmentCityWeatherDetailsBinding
import com.mohdabbas.cityweather.ui.SharedViewModel
import com.mohdabbas.cityweather.util.WeatherUtil.temperatureFromKelvinToCelsius
import dagger.hilt.android.AndroidEntryPoint

/**
 * Created by Mohammad Abbas
 * On: 5/14/22.
 */
@AndroidEntryPoint
class CityWeatherDetailsFragment : Fragment(R.layout.fragment_city_weather_details) {

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


    private val cityWeatherDetailsFragmentArgs: CityWeatherDetailsFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sharedViewModel.getCityWeatherInfoByCityId(cityWeatherDetailsFragmentArgs.cityId)?.let {
            populateViews(it)
        }
    }

    private fun populateViews(cityWeather: CityWeather) {
        binding.apply {
            val weather = cityWeather.weather.firstOrNull()

            tvWeatherCondition.text =
                weather?.main ?: getString(R.string.unavailable_weather_condition)
            tvTemp.text = cityWeather.main.temp.temperatureFromKelvinToCelsius().toString()

            Glide.with(requireContext())
                .load("http://openweathermap.org/img/wn/${weather?.icon ?: ""}@2x.png")
                .centerCrop()
                .placeholder(R.drawable.ic_launcher_background)
                .into(ivWeatherIcon)

            wind.tvTitle.text = getString(R.string.wind)
            wind.tvValue.text = cityWeather.wind.speed.toInt().toString()

            humidity.tvTitle.text = getString(R.string.humidity)
            humidity.tvValue.text = cityWeather.main.humidity.toInt().toString()

            pressure.tvTitle.text = getString(R.string.pressure)
            pressure.tvValue.text = cityWeather.main.pressure.toInt().toString()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}