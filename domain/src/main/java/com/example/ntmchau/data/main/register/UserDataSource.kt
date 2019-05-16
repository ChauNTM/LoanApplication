package com.example.ntmchau.data.main.register

import com.example.ntmchau.data.entity.UserInfo
import io.reactivex.Single

interface UserDataSource {
    fun getProvinces(): Single<MutableList<String>>
    fun sendRegisterLoanRequest(
        fullName: String,
        phoneNumber: String,
        nationalIdNumber: String,
        monthlyIncome: Long,
        province: String
    ): Single<UserInfo>
}