package com.example.ntmchau.loanapp.base

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.example.ntmchau.loanapp.di.Injectable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class ViewModelFragment : Fragment(), Injectable {

    abstract var viewModelFactory: ViewModelProvider.Factory

    lateinit var viewModelBuilder: ViewModelBuilder

    private val compositeDisposable = CompositeDisposable()


    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (!this::viewModelBuilder.isInitialized) {
            viewModelBuilder = ViewModelBuilder()
        }
    }

    abstract fun initViewModels()

    protected fun <T : ViewModel> provideViewModel(classType: Class<T>): T {
        return activity?.let { viewModelBuilder.provideViewModel(viewModelFactory, it, classType) }
            ?: ViewModelProviders.of(this, viewModelFactory).get(classType)
    }

    override fun onStop() {
        super.onStop()
        compositeDisposable.clear()
    }

    protected fun manage(disposable: Disposable) {
        compositeDisposable.addAll(disposable)
    }
}