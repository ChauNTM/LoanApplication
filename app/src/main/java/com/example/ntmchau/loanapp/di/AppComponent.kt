package com.example.ntmchau.loanapp.di

import com.example.ntmchau.loanapp.LoanApp
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [AndroidInjectionModule::class, AppModule::class, BuildersModule::class])
interface AppComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(app: LoanApp): Builder

        fun build(): AppComponent

    }

    fun inject(app: LoanApp)
}