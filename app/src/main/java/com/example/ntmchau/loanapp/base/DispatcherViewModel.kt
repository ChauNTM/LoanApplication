package com.example.ntmchau.loanapp.base

import com.example.ntmchau.data.base.viewstate.BaseViewState
import com.example.ntmchau.data.base.viewstate.Reducer

abstract class DispatcherViewModel<S : BaseViewState, A : Any> : BaseViewModel<S>() {
    abstract val reducer: Reducer<S, A>

    fun dispatch(action: A) {
        val currentViewState = currentViewState()
        updateViewState(reducer.reduce(currentViewState, action), actionName = action::class.java.simpleName)
        dispatchAsync(currentViewState, action)
    }

    abstract fun dispatchAsync(state: S, action: A)
}