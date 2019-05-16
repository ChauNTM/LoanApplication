package com.example.ntmchau.loanapp.main.register

import android.content.Context
import android.util.Log
import com.example.ntmchau.data.main.register.*
import com.example.ntmchau.loanapp.R
import com.example.ntmchau.loanapp.base.DispatcherViewModel
import com.example.ntmchau.loanapp.utils.VerifyUtils
import javax.inject.Inject

class RegisterViewModel @Inject constructor(
    private val appContext: Context,
    private val verifyUtils: VerifyUtils,
    private val getProvincesUseCase: GetProvincesUseCase,
    private val sendRequestRegisterUseCase: SendRequestRegisterUseCase
) : DispatcherViewModel<RegisterViewState, RegisterAction>() {

    companion object {
        private val TAG = RegisterViewModel::class.java.simpleName
    }

    override val reducer = RegisterReducer()

    override val defaultState: RegisterViewState = RegisterViewState()

    override fun init() {
        dispatch(GetProvinces)
    }

    override fun dispatchAsync(state: RegisterViewState, action: RegisterAction) {
        when (action) {
            is GetProvinces -> getProvinces()
            is SendRequestRegister -> sendRequestRegister(action.fullName, action.phoneNumber, action.nationalIdNumber, action.monthlyIncome, action.province)
        }
    }

    private fun getProvinces() {
        manage(getProvincesUseCase.execute().subscribe(
            { provinces -> dispatch(UpdateProvinces(provinces)) },
            { dispatch(ShowConnectionError) }
        ))
    }

    private fun sendRequestRegister(fullName: String, phoneNumber: String, nationalIdNumber: String, monthlyIncome: Long, province: String) {
        Log.d(TAG, "sendRequestRegisterUseCase")

        manage(sendRequestRegisterUseCase.execute(SendRequestParams(fullName, phoneNumber, nationalIdNumber, monthlyIncome, province)).subscribe(
            { userInfo -> dispatch(UpdateSendRequestResult(userInfo)) },
            { dispatch(ShowConnectionError) }
        ))
    }

    fun verifyUserInfo() {

        if (currentViewState().fullName.isEmpty() || currentViewState().phoneNumber.isEmpty() || currentViewState().province.isEmpty() || currentViewState().monthlyIncome.isEmpty()) {
            return dispatch(NotifyInvalidUserInfo(appContext.getString(R.string.user_info_field_not_blank)))
        }

        val phoneError = verifyUtils.verifyPhoneNumber(currentViewState().phoneNumber)
        if (!phoneError.first) return dispatch(NotifyInvalidUserInfo(phoneError.second?.error))

        if (currentViewState().nationalIdNumber.isNotEmpty()) {
            val nationalIdNumberError = verifyUtils.verifyNationalIdNumber(currentViewState().nationalIdNumber)
            if (!nationalIdNumberError.first) return dispatch(NotifyInvalidUserInfo(nationalIdNumberError.second?.error))
        }

        if (reducer.mapMonthlyIncomeStringToValue(currentViewState().monthlyIncome) < VerifyUtils.LOWEST_MONTHLY_INCOME) {
            return dispatch(NotifyInvalidUserInfo(appContext.getString(R.string.invalid_monthly_income)))
        }

        currentViewState().errorMessage = null
        dispatch(SendRequestRegister(currentViewState().fullName, currentViewState().phoneNumber, currentViewState().nationalIdNumber, reducer.mapMonthlyIncomeStringToValue(currentViewState().monthlyIncome), currentViewState().province))
    }

}