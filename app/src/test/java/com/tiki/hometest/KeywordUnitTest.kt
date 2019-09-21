package com.tiki.hometest

import com.tiki.hometest.ui.Utils
import org.junit.Test
import org.junit.Assert.*

class KeywordUnitTest {
    @Test
    fun validateOneWord(){
        validate("foo", "foo","Get Keyword method is not correct with one word")
        validate("foo bar", "foo\nbar","Get Keyword method is not correct with two words")
        validate("anh chinh la thanh xuan cua em", "anh chinh la\nthanh xuan cua em","Get Keyword method is not correct with many words")
    }

    private fun validate(input: String, expectedValue: String, message: String){
        val actualValue = Utils.getKeywordString(input)
        assertTrue(message, actualValue == expectedValue)
    }
}