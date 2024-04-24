package com.example.mviprojectex1.util

import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class UtilsTest {
    private lateinit var utils: Utils

    @Before
    fun setUp() {
        utils = Utils()
    }

    @Test
    fun isSuccessValueReturn() {
        val result = utils.addFun(4, 5)
        assertEquals(9, result)
    }
    @Test
    fun isFailureValueReturn() {
        val result = utils.addFun(4, 5)
        assertNotEquals(8, result)
    }

    @Test
    fun passwordValidateSuccess(){
        val result = utils.isValidPassword("234566")
        assertEquals(true,result)
    }
    @Test
    fun passworkdValidateFail(){
        val result = utils.isValidPassword("")
        assertEquals(false,result)
    }
    @Test
    fun emptyPasswordValidateFail(){
        val result = utils.isValidPassword("")
        assertEquals(false,result)
    }

    @Test
    fun returnEmptyListTrueTwoMaxEmpty(){
        val list:List<Int> = mutableListOf()
        val result = utils.getTwoMaxNumber(list)
        assertEquals(null,result)
    }
    @Test
    fun successTwoMaxNumber(){
        val list = listOf(2,3,5,6,5,4,9,8)
        val result = utils.getTwoMaxNumber(list)
        assertEquals(listOf(9,8),result)
    }
    @Test
    fun failureTwoMaxNumber(){
        val list = listOf(0,2,5)
        val result = utils.getTwoMaxNumber(list)
        assertNotEquals(listOf(0,2),result)
    }

}