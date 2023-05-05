package com.example.forecast.util

import java.math.RoundingMode
import java.text.DecimalFormat

class FormatText {

    fun formatString(str: String): String {
        var words: List<String> = str.split(" ")
        var changedWord = ""
        for(i in words.indices) {
            for(j in 0 until words[i].length) {
                if(j==0) {
                    changedWord += words[i][j].uppercaseChar()
                } else {
                    changedWord += words[i][j]
                }
            }
            changedWord += " "
        }

        return changedWord
    }

    fun roundOffDecimal(number: Double): Double? {
        val df = DecimalFormat("#.#")
        df.roundingMode = RoundingMode.CEILING
        return df.format(number).toDouble()
    }
}