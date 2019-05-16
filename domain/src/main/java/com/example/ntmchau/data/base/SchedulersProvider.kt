package com.example.ntmchau.data.base

import io.reactivex.CompletableTransformer
import io.reactivex.MaybeTransformer
import io.reactivex.ObservableTransformer
import io.reactivex.SingleTransformer


interface SchedulersProvider {

    fun completableTransformer(): CompletableTransformer

    fun <T> observableTransformer(): ObservableTransformer<T, T>

    fun <T> singleTransformer(): SingleTransformer<T, T>

    fun <T> maybeTransformer(): MaybeTransformer<T, T>

}