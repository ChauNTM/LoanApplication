package com.example.ntmchau.data.main.register

import android.util.Log
import com.example.ntmchau.data.entity.UserInfo
import com.example.ntmchau.data.retrofit.ApiInterface
import io.reactivex.Single
import javax.inject.Inject

class UserRepository @Inject constructor(private val apiInterface: ApiInterface) :
    UserDataSource {

    override fun sendRegisterLoanRequest(
        fullName: String,
        phoneNumber: String,
        nationalIdNumber: String,
        monthlyIncome: Long,
        province: String
    ): Single<UserInfo> {
        return apiInterface.sendRequestRegister(fullName, phoneNumber, nationalIdNumber, monthlyIncome, province)
            .map { it.toUserInfo() }
    }

    override fun getProvinces(): Single<MutableList<String>> {
        return apiInterface.getProvinces().map {
            Log.d("", "getProvinces provinces=$it")
            it.provinces
        }
    }
}