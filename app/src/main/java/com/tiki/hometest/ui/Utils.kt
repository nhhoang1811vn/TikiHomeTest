package com.tiki.hometest.ui

import kotlin.math.abs


object Utils {

    fun getKeywordString(input: String) : String{
        val trimStr = input.trim()
        val splited = trimStr.split(" ")
        val totalcharacters = trimStr.length

        var indexMin = -1
        var min = Int.MAX_VALUE
        var firstPartCharacter = 0
        for (i in 0 until splited.size){
            firstPartCharacter+= (splited[i].length + 1)
            val secondPartCharacter = totalcharacters - firstPartCharacter
            val diff = abs((firstPartCharacter-1) - secondPartCharacter)
            if (diff < min){
                min = diff
                indexMin = i
            }
        }
        var output = ""
        for (i in 0 until splited.size){
            output += splited[i]
            if (i == indexMin){
                output += "\n"
            }
            else if (i < splited.size - 1){
                output += " "
            }
        }
        return output
    }
}