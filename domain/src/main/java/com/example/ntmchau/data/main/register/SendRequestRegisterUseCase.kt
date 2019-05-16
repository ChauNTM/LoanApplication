package com.example.ntmchau.data.main.register

import com.example.ntmchau.data.base.SchedulersProvider
import com.example.ntmchau.data.base.usecase.SingleUseCase
import com.example.ntmchau.data.entity.UserInfo
import io.reactivex.Single
import javax.inject.Inject

class SendRequestRegisterUseCase @Inject constructor(
    private val userRepository: UserDataSource,
    schedulersProvider: SchedulersProvider
) : SingleUseCase<SendRequestParams, UserInfo>(schedulersProvider) {
    override fun createUseCaseSingle(param: SendRequestParams): Single<UserInfo> {
        return userRepository.sendRegisterLoanRequest(param.fullName, param.phoneNumber, param.nationalIdNumber, param.monthlyIncome, param.province)
    }
}

class SendRequestParams(val fullName: String, val phoneNumber: String, val nationalIdNumber: String, val monthlyIncome: Long, val province: String)