package com.example.ntmchau.data.base.usecase

import com.example.ntmchau.data.base.SchedulersProvider
import com.example.ntmchau.data.base.trace
import io.reactivex.Single


abstract class SingleUseCase<in P, R>(private val schedulersProvider: SchedulersProvider) {

    private lateinit var observable: Single<R>

    fun execute(param: P): Single<R> {
        observable = createUseCaseSingle(param).trace().compose(schedulersProvider.singleTransformer())
        return observable
    }

    protected abstract fun createUseCaseSingle(param: P): Single<R>

}
