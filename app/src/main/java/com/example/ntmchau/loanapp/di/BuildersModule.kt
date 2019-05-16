package com.example.ntmchau.loanapp.di

import com.example.ntmchau.loanapp.main.MainActivity
import com.example.ntmchau.loanapp.main.MainActivityModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class BuildersModule {
    @ActivityScope
    @ContributesAndroidInjector(modules = [MainActivityModule::class, FragmentBuilderModule::class])
    abstract fun bindMainActivity(): MainActivity
}