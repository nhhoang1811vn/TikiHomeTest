package com.tiki.hometest.ui

import android.util.Log
import kotlin.math.abs


object Utils {

    fun getKeywordString(input: String) : String{
        val trimStr = input.trim()
        val splited = trimStr.split(" ")


        var min = Int.MAX_VALUE
        var firstStringRes = ""
        var secondStringRes = ""
        for (i in 0 until splited.size){
            val firstString = getStringJoinSpace(splited,0,i+1)
            val secondString = getStringJoinSpace(splited,i+1, splited.size)
            val diff = abs(firstString.length - secondString.length)
            if (diff < min){
                min = diff
                firstStringRes = firstString
                secondStringRes = secondString
            }
        }
        if (secondStringRes.isEmpty())
            return firstStringRes
        return firstStringRes + "\n" + secondStringRes
    }
    private fun getStringJoinSpace(splited: List<String>, leftIndex: Int, rightIndex: Int) : String{
        if (leftIndex > rightIndex){
            return ""
        }
        val newData = splited.subList(leftIndex, rightIndex)
        val string = newData.joinToString(" ")
        return string
    }
}