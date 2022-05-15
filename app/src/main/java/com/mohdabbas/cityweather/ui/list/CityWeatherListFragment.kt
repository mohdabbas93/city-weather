package com.mohdabbas.cityweather.ui.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.mohdabbas.cityweather.R
import com.mohdabbas.cityweather.databinding.FragmentCityWeatherListBinding

/**
 * Created by Mohammad Abbas
 * On: 5/14/22.
 */
class CityWeatherListFragment : Fragment(R.layout.fragment_city_weather_list) {
    private var _binding: FragmentCityWeatherListBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCityWeatherListBinding.inflate(layoutInflater, container, false)

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}