package com.example.ntmchau.loanapp.main

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.ntmchau.loanapp.R
import com.example.ntmchau.loanapp.base.BaseActivity
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

class MainActivity : BaseActivity(), HasSupportFragmentInjector {

    @Inject
    override lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun initViewModels() {

    }
}
