package com.practice.myapplication.utility

object StringUtils {
    fun capitalizeFirstLetter(word: String): String {
        if (word.isEmpty()) {
            return word
        }

        val firstChar = word[0].uppercase()
        val restOfWord = word.substring(1)
        return firstChar + restOfWord
    }

}
