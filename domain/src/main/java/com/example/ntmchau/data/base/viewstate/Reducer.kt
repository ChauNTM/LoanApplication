package com.example.ntmchau.data.base.viewstate

interface Reducer<S, A> {
    fun reduce(state: S, action: A): S
}