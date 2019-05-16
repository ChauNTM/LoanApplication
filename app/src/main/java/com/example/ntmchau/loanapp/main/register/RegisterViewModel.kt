package com.example.ntmchau.loanapp.main.register

import com.example.ntmchau.data.main.register.*
import com.example.ntmchau.loanapp.LoanApp
import com.example.ntmchau.loanapp.R
import com.example.ntmchau.loanapp.base.DispatcherViewModel
import com.example.ntmchau.loanapp.utils.VerifyUtils
import javax.inject.Inject

class RegisterViewModel @Inject constructor(
    private val app: LoanApp,
    private val verifyUtils: VerifyUtils,
    private val getProvincesUseCase: GetProvincesUseCase,
    private val sendRequestRegister: SendRequestRegister
) : DispatcherViewModel<RegisterViewState, RegisterAction>() {

    override val reducer = RegisterReducer()

    override val defaultState: RegisterViewState = RegisterViewState()

    override fun init() {
        dispatch(GetProvinces)
    }

    override fun dispatchAsync(state: RegisterViewState, action: RegisterAction) {
        when (action) {
            is GetProvinces -> getProvinces()
        }
    }

    private fun getProvinces() {
        manage(getProvincesUseCase.execute().subscribe(
            { provinces -> dispatch(UpdateProvinces(provinces)) },
            { dispatch(ShowConnectionError) }
        ))
    }

    private fun sendRequestRegister() {
        val fullName = currentViewState().fullName
        val phoneNumber = currentViewState().phoneNumber
        val nationalIdNumber = currentViewState().nationalIdNumber
        val monthlyIncome = reducer.mapMonthlyIncomeStringToValue(currentViewState().monthlyIncome)
        val province = currentViewState().province
        manage(sendRequestRegister.execute(SendRequestParams(fullName, phoneNumber, nationalIdNumber, monthlyIncome, province)).subscribe(
            { userInfo -> dispatch(UpdateSendRequestResult(userInfo)) },
            { dispatch(ShowConnectionError) }
        ))
    }

    fun verifyUserInfo() {

        if (currentViewState().fullName.isEmpty() || currentViewState().phoneNumber.isEmpty() || currentViewState().province.isEmpty() || currentViewState().monthlyIncome.isEmpty()) {
            return dispatch(NotifyInvalidUserInfo(app.getString(R.string.user_info_field_not_blank)))
        }

        val phoneError = verifyUtils.verifyPhoneNumber(currentViewState().phoneNumber)
        if (!phoneError.first) return dispatch(NotifyInvalidUserInfo(phoneError.second?.error))

        if (currentViewState().nationalIdNumber.isNotEmpty()) {
            val nationalIdNumberError = verifyUtils.verifyNationalIdNumber(currentViewState().nationalIdNumber)
            if (!nationalIdNumberError.first) return dispatch(NotifyInvalidUserInfo(nationalIdNumberError.second?.error))
        }

        NotifyInvalidUserInfo(null)
        sendRequestRegister()
    }

}