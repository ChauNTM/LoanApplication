package com.example.ntmchau.data.base.usecase

import com.example.ntmchau.data.base.SchedulersProvider
import com.example.ntmchau.data.base.trace
import io.reactivex.Maybe


abstract class MaybeUseCase<in P, R>(private val schedulersProvider: SchedulersProvider) {

    private lateinit var maybe: Maybe<R>

    fun execute(param: P): Maybe<R> {
        maybe = createUseCaseMaybe(param).trace().compose(schedulersProvider.maybeTransformer())
        return maybe
    }

    protected abstract fun createUseCaseMaybe(param: P): Maybe<R>

}
