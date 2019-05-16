package com.example.ntmchau.loanapp.helpers

import androidx.lifecycle.Observer
import com.nhaarman.mockito_kotlin.argumentCaptor
import com.nhaarman.mockito_kotlin.reset
import com.nhaarman.mockito_kotlin.times
import org.junit.Assert
import org.mockito.Mockito

inline fun <reified T : Any> Observer<T>.verifyViewStates(vararg viewStates: T) {
    argumentCaptor<T>().apply {
        Mockito.verify(this@verifyViewStates, times(viewStates.size)).onChanged(capture())
        viewStates.forEach {
            Assert.assertEquals(it, allValues[viewStates.indexOf(it)])
        }
        reset(this@verifyViewStates)
    }
}
