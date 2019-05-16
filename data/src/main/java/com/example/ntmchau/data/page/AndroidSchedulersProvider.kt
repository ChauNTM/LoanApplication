package com.example.ntmchau.data.page

import com.example.ntmchau.data.base.SchedulersProvider
import io.reactivex.CompletableTransformer
import io.reactivex.MaybeTransformer
import io.reactivex.ObservableTransformer
import io.reactivex.SingleTransformer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class AndroidSchedulersProvider @Inject constructor() : SchedulersProvider {

    override fun completableTransformer(): CompletableTransformer {
        return CompletableTransformer { it ->
            it.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
        }
    }

    override fun <T> singleTransformer(): SingleTransformer<T, T> {
        return SingleTransformer { it ->
            it.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
        }
    }

    override fun <T> observableTransformer(): ObservableTransformer<T, T> {
        return ObservableTransformer { it ->
            it.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
        }
    }

    override fun <T> maybeTransformer(): MaybeTransformer<T, T> {
        return MaybeTransformer { it ->
            it.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
        }
    }

}