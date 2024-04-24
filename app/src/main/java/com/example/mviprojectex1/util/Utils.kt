package com.example.mviprojectex1.util

class Utils {
    fun addFun(i: Int, i1: Int): Int {
        return i + i1
    }

    fun isValidPassword(string: String): Boolean {
        return string.isNotEmpty() && string.length > 5
    }

    fun getTwoMaxNumber(list: List<Int>): MutableList<Int>? {
        if (list.isNullOrEmpty())
            return null

        var result = mutableListOf<Int>(0, 0)
        for (i in list.indices) when {
            list[i] > result[0] -> {
                result[1] = result[0]
                result[0] = list[i]

            }
            list[i] > result[1] -> result[1] = list[i]
        }
        return result
    }

}