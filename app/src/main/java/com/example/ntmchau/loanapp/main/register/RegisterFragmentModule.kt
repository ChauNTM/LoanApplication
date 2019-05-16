package com.example.ntmchau.loanapp.main.register

import com.example.ntmchau.loanapp.LoanApp
import com.example.ntmchau.loanapp.R
import dagger.Module
import dagger.Provides

@Module
class RegisterFragmentModule {

    @Provides
    fun provideMonthlyIncomeList(app: LoanApp): MutableList<Int> {
        return app.resources.getIntArray(R.array.monthly_income_array).toMutableList()
    }
}