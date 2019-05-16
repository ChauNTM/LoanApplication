package com.example.ntmchau.data.main.register

import com.example.ntmchau.data.base.viewstate.Reducer
import com.example.ntmchau.data.entity.UserInfo
import com.example.ntmchau.data.main.home.HomeAction
import com.example.ntmchau.data.main.home.HomeViewState

class RegisterReducer : Reducer<RegisterViewState, RegisterAction> {

    override fun reduce(state: RegisterViewState, action: RegisterAction): RegisterViewState {
        return RegisterViewState(
            userInfo = reduceUserInfo(state, action),
            provinces = reduceProvinces(state, action),
            monthlyIncomeList = reduceMonthlyIncomeList(state, action),
            fullName = state.fullName,
            phoneNumber = state.phoneNumber,
            nationalIdNumber = state.nationalIdNumber,
            province = state.province,
            monthlyIncome = state.monthlyIncome,
            errorMessage = reduceErrorMessage(state, action)
        )
    }

    private fun reduceUserInfo(state: RegisterViewState, action: RegisterAction): UserInfo {
        return when (action) {
            is UpdateSendRequestResult -> action.userInfo
            else -> state.userInfo
        }
    }

    private fun reduceErrorMessage(state: RegisterViewState, action: RegisterAction): String? {
        return when (action) {
            is NotifyInvalidUserInfo -> action.errorMessage
            else -> state.errorMessage
        }
    }

    private fun mapMonthlyIncomeValuesToString(monthlyIncomeList: MutableList<Int>): MutableList<String> {
        val result = mutableListOf<String>()
        monthlyIncomeList.apply { this.sortDescending() }.forEach { value -> result.add("> $value") }
        result.add("< ${monthlyIncomeList.last()}")
        return result
    }

    private fun reduceMonthlyIncomeList(state: RegisterViewState, action: RegisterAction): MutableList<String> {
        return when (action) {
            is UpdateMonthlyIncomeList -> mapMonthlyIncomeValuesToString(action.monthlyIncomeList)
            else -> state.monthlyIncomeList
        }
    }

    private fun reduceProvinces(state: RegisterViewState, action: RegisterAction): MutableList<String> {
        return when (action) {
            is UpdateProvinces -> action.provinces
            else -> state.provinces
        }
    }

    fun mapMonthlyIncomeStringToValue(monthlyIncomeString: String): Long {
        return if (monthlyIncomeString.first() == '>') return monthlyIncomeString.substring(2).toLong() + 1
        else 1
    }

}