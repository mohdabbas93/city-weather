package com.mohdabbas.cityweather.ui.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.mohdabbas.cityweather.R
import com.mohdabbas.cityweather.data.CityWeather
import com.mohdabbas.cityweather.data.Result
import com.mohdabbas.cityweather.databinding.FragmentCityWeatherDetailsBinding
import com.mohdabbas.cityweather.ui.SharedViewModel
import com.mohdabbas.cityweather.util.WeatherUtil.speedFromMeterPerSecToKmPerHour
import com.mohdabbas.cityweather.util.WeatherUtil.temperatureFromKelvinToCelsius
import com.mohdabbas.cityweather.util.hideViews
import com.mohdabbas.cityweather.util.showViews
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

        setupObservers()

        return binding.root
    }

    private val cityWeatherDetailsFragmentArgs: CityWeatherDetailsFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sharedViewModel.getCityWeatherInfoByCityId(cityWeatherDetailsFragmentArgs.cityId)
    }

    private fun setupObservers() {
        sharedViewModel.cityWeatherDetails.observe(viewLifecycleOwner) { result ->
            when (result) {
                is Result.Loading -> {
                    binding.apply {
                        hideViews(detailsContainer)
                        showViews(loading)
                    }
                }
                is Result.Error -> {
                    Toast.makeText(requireContext(), "Error..", Toast.LENGTH_SHORT).show()
                    binding.apply {
                        hideViews(detailsContainer, loading)
                    }
                }
                is Result.Success -> {
                    binding.apply {
                        hideViews(loading)
                        showViews(detailsContainer)
                    }
                    populateViews(result.data)
                }
            }
        }
    }

    private fun populateViews(cityWeather: CityWeather) {
        binding.apply {
            val weather = cityWeather.weather.firstOrNull()

            tvCityCountryName.text = getString(
                R.string.city_name_country_name,
                cityWeather.city.name,
                cityWeather.city.country
            )

            tvWeatherCondition.text =
                weather?.main ?: getString(R.string.unavailable_weather_condition)
            tvTemp.text = getString(
                R.string.temp_format,
                cityWeather.main.temp.temperatureFromKelvinToCelsius()
            )

            Glide.with(requireContext())
                .load("http://openweathermap.org/img/wn/${weather?.icon ?: ""}@2x.png")
                .centerCrop()
                .placeholder(R.drawable.ic_launcher_background)
                .into(ivWeatherIcon)

            wind.tvTitle.text = getString(R.string.wind)
            wind.tvValue.text =
                getString(
                    R.string.wind_speed_format,
                    cityWeather.wind.speed.speedFromMeterPerSecToKmPerHour()
                )

            humidity.tvTitle.text = getString(R.string.humidity)
            humidity.tvValue.text =
                getString(R.string.humidity_format, cityWeather.main.humidity.toInt())

            pressure.tvTitle.text = getString(R.string.pressure)
            pressure.tvValue.text =
                getString(R.string.pressure_format, cityWeather.main.pressure.toInt())
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}