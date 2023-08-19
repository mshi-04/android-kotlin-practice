package com.example.kotlinpractice

import com.example.kotlinpractice.utility.StringUtils
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun capitalizeFirstLetter_isCorrect() {
        StringUtils.capitalizeFirstLetter("")
        StringUtils.capitalizeFirstLetter("abc")
        StringUtils.capitalizeFirstLetter("Def")
        StringUtils.capitalizeFirstLetter("gHi")
        StringUtils.capitalizeFirstLetter("jkL")
        StringUtils.capitalizeFirstLetter("こんにちは")
    }
}