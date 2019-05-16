package com.example.ntmchau.data.base.usecase

import com.example.ntmchau.data.base.SchedulersProvider
import com.example.ntmchau.data.base.trace
import io.reactivex.Completable


abstract class CompletableUseCase<in P>(private val schedulersProvider: SchedulersProvider) {

    private lateinit var completable: Completable

    fun execute(param: P): Completable {
        completable = createUseCaseCompletable(param).trace().compose(schedulersProvider.completableTransformer())
        return completable
    }

    protected abstract fun createUseCaseCompletable(param: P): Completable
}
