package com.example.ntmchau.data.base

import io.reactivex.Completable
import io.reactivex.Maybe
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.exceptions.CompositeException

class RxTraceException: Exception()

inline fun Completable.trace(): Completable {
    val traceException = RxTraceException()
    return this.onErrorResumeNext { error: Throwable ->
        throw CompositeException(error, traceException)
    }
}

inline fun <T> Maybe<T>.trace(): Maybe<T> {
    val traceException = RxTraceException()
    return this.onErrorResumeNext { error: Throwable ->
        throw CompositeException(error, traceException)
    }
}

inline fun <T> Single<T>.trace(): Single<T> {
    val traceException = RxTraceException()
    return this.onErrorResumeNext { error: Throwable ->
        throw CompositeException(error, traceException)
    }
}

inline fun <T> Observable<T>.trace(): Observable<T> {
    val traceException = RxTraceException()
    return this.onErrorResumeNext { error: Throwable ->
        throw CompositeException(error, traceException)
    }
}
