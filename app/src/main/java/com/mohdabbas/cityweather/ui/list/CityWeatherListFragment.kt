package com.mohdabbas.cityweather.ui.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.mohdabbas.cityweather.R
import com.mohdabbas.cityweather.databinding.FragmentCityWeatherListBinding
import com.mohdabbas.cityweather.ui.SharedViewModel
import com.mohdabbas.cityweather.ui.list.adapter.CityWeatherAdapter
import dagger.hilt.android.AndroidEntryPoint

/**
 * Created by Mohammad Abbas
 * On: 5/14/22.
 */
@AndroidEntryPoint
class CityWeatherListFragment : Fragment(R.layout.fragment_city_weather_list) {
    private var _binding: FragmentCityWeatherListBinding? = null
    private val binding get() = _binding!!

    private val cityWeatherAdapter = CityWeatherAdapter(listOf())

    private val sharedViewModel: SharedViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCityWeatherListBinding.inflate(layoutInflater, container, false)

        setupRecyclerView()
        setupObservers()
        setupSearch()

        return binding.root
    }

    private fun setupRecyclerView() {
        binding.rvCityWeather.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = cityWeatherAdapter
        }
    }

    private fun setupObservers() {
        sharedViewModel.citiesWeather.observe(viewLifecycleOwner) {
            cityWeatherAdapter.updateData(it)
        }
    }

    private fun setupSearch() {
        binding.etSearch.setOnEditorActionListener { textView, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH && textView.text.isNotBlank()) {
                val citiesWeather =
                    sharedViewModel.searchByCityName(textView.text.trim().toString())
                cityWeatherAdapter.updateData(citiesWeather)
            }
            false
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sharedViewModel.getCitiesWeather()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}