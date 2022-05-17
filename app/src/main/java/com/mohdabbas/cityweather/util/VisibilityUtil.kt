package com.mohdabbas.cityweather.util

import android.view.View

/**
 * Created by Mohammad Abbas
 * On: 5/17/22.
 */

/**
 * This function is used to show the views by setting their visibility to VISIBLE.
 *
 * @param views The views to be showed
 */
fun showViews(vararg views: View) {
    views.forEach { it.visibility = View.VISIBLE }
}

/**
 * This function is used to hide the views by setting their visibility to GONE.
 *
 * @param views The views to be hidden
 */
fun hideViews(vararg views: View) {
    views.forEach { it.visibility = View.GONE }
}