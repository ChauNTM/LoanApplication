package com.example.ntmchau.loanapp.base

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.ntmchau.data.base.viewstate.BaseViewState
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class BaseViewModel<T : BaseViewState> : ViewModel() {

    companion object {
        const val UNKNOWN_ERROR = "Unknown errorMessage"
        private val TAG = BaseViewModel::class.java.simpleName
    }

    abstract val defaultState: T

    private val viewState: MutableLiveData<T> by lazy { provideViewStateType() }

    private val compositeDisposable by lazy {
        CompositeDisposable()
    }

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }

    protected fun manage(disposable: Disposable) {
        compositeDisposable.addAll(disposable)
    }

    protected fun updateViewState(newViewState: T, actionName: String? = null) {
        viewState.value = newViewState
    }

    protected open fun provideViewStateType() = MutableLiveData<T>()

    fun currentViewState(): T {
        return viewState.value ?: defaultState
    }

    fun viewState(): LiveData<T> = viewState

    fun isViewStateEmpty(): Boolean {
        return viewState.value == null
    }

    abstract fun init()

}