package com.example.ntmchau.data.base.usecase

import com.example.ntmchau.data.base.SchedulersProvider
import com.example.ntmchau.data.base.trace
import io.reactivex.Observable


abstract class ObservableUseCase<in P, R>(private val schedulersProvider: SchedulersProvider) {

    private lateinit var observable: Observable<R>

    fun execute(param: P): Observable<R> {
        observable = createUseCaseObservable(param).trace().compose(schedulersProvider.observableTransformer())
        return observable
    }

    protected abstract fun createUseCaseObservable(param: P): Observable<R>

}
