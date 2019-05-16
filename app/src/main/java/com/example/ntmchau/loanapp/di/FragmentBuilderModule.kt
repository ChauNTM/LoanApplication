package com.example.ntmchau.loanapp.di

import com.example.ntmchau.loanapp.main.home.HomeFragment
import com.example.ntmchau.loanapp.main.home.HomeFragmentModule
import com.example.ntmchau.loanapp.main.register.RegisterFragment
import com.example.ntmchau.loanapp.main.register.RegisterFragmentModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBuilderModule {

    @FragmentScope
    @ContributesAndroidInjector(modules = [(HomeFragmentModule::class)])
    abstract fun bindHomeFragment(): HomeFragment

    @FragmentScope
    @ContributesAndroidInjector(modules = [(RegisterFragmentModule::class)])
    abstract fun bindRegisterFragment(): RegisterFragment

}