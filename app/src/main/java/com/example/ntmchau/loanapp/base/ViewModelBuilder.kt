package com.example.ntmchau.loanapp.base

import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders

class ViewModelBuilder {
    fun <T: ViewModel> provideViewModel(viewModelFactory: ViewModelProvider.Factory, activity: FragmentActivity, classType: Class<T>): T {
        return ViewModelProviders.of(activity, viewModelFactory).get(classType)
    }
}