package com.example.ntmchau.loanapp.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

abstract class BaseActivity : AppCompatActivity(), HasSupportFragmentInjector{
    @Inject
    lateinit var fragmentInjector: DispatchingAndroidInjector<Fragment>

    lateinit var viewModelBuilder: ViewModelBuilder

    abstract var viewModelFactory: ViewModelProvider.Factory

    private val compositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (!this::viewModelBuilder.isInitialized) {
            viewModelBuilder = ViewModelBuilder()
        }
    }

    abstract fun initViewModels()

    protected fun <T : ViewModel> provideViewModel(activity: FragmentActivity, classType: Class<T>): T {
        return viewModelBuilder.provideViewModel(viewModelFactory, activity, classType)
    }

    override fun supportFragmentInjector(): AndroidInjector<Fragment> =fragmentInjector

    override fun onStop() {
        super.onStop()
        compositeDisposable.clear()
    }
}