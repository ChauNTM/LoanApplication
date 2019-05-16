package com.example.ntmchau.data.main.register

import com.example.ntmchau.data.base.viewstate.BaseViewState
import com.example.ntmchau.data.entity.UserInfo

data class RegisterViewState(
    val userInfo: UserInfo = UserInfo(0, "", "", "", 0L, ""),
    val provinces: MutableList<String> = mutableListOf(),
    val monthlyIncomeList: MutableList<String> = mutableListOf(),
    var fullName: String = "", var phoneNumber: String = "", var nationalIdNumber: String = "", var monthlyIncome: String = "", var province: String = "",
    val errorMessage: String? = null
) : BaseViewState

sealed class RegisterAction

object GetProvinces : RegisterAction()
data class UpdateProvinces(val provinces: MutableList<String>) : RegisterAction()
data class UpdateMonthlyIncomeList(val monthlyIncomeList: MutableList<Int>): RegisterAction()
data class NotifyInvalidUserInfo(val errorMessage: String?): RegisterAction()
data class UpdateSendRequestResult(val userInfo: UserInfo) : RegisterAction()

object ShowConnectionError : RegisterAction()