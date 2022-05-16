package com.mohdabbas.cityweather.util

import android.content.Context
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Created by Mohammad Abbas
 * On: 5/16/22.
 */
@RunWith(AndroidJUnit4::class)
class JsonParserTest {

    private lateinit var jsonParser: JsonParser
    private lateinit var context: Context

    @Before
    fun setup() {
        context = InstrumentationRegistry.getInstrumentation().targetContext
        jsonParser = JsonParser(context)
    }

    @Test
    fun should_success_when_parseJsonIsCorrect() {
        // TODO: Implement this
    }

    fun should_fail_when_parseJsonIsInCorrect() {
        // TODO: Implement this
    }
}