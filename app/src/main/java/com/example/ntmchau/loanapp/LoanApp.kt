package com.example.ntmchau.loanapp

import android.app.Activity
import android.app.Application
import androidx.lifecycle.LifecycleObserver
import com.example.ntmchau.loanapp.di.AppInjector
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

class LoanApp: Application(), HasActivityInjector, LifecycleObserver {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    override fun activityInjector(): AndroidInjector<Activity> = dispatchingAndroidInjector

    override fun onCreate() {
        super.onCreate()

        AppInjector.init(this)
    }

}