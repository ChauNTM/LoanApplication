package com.example.ntmchau.data.base.usecase.unit

import com.example.ntmchau.data.base.SchedulersProvider
import com.example.ntmchau.data.base.usecase.MaybeUseCase
import io.reactivex.Maybe


abstract class MaybeUnitUseCase<R>(schedulersProvider: SchedulersProvider) : MaybeUseCase<Unit, R>(schedulersProvider) {

    fun execute(): Maybe<R> = execute(Unit)

    override fun createUseCaseMaybe(param: Unit) = createUseCaseMaybe()

    protected abstract fun createUseCaseMaybe(): Maybe<R>

}