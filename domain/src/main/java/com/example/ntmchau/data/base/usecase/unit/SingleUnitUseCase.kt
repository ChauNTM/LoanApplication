package com.example.ntmchau.data.base.usecase.unit

import com.example.ntmchau.data.base.SchedulersProvider
import com.example.ntmchau.data.base.usecase.SingleUseCase
import io.reactivex.Single


abstract class SingleUnitUseCase<R>(schedulersProvider: SchedulersProvider) :
    SingleUseCase<Unit, R>(schedulersProvider) {

    fun execute(): Single<R> = execute(Unit)

    override fun createUseCaseSingle(param: Unit) = createUseCaseSingle()

    protected abstract fun createUseCaseSingle(): Single<R>

}
