package com.example.ntmchau.data.main.register

import com.example.ntmchau.data.base.SchedulersProvider
import com.example.ntmchau.data.base.usecase.unit.SingleUnitUseCase
import io.reactivex.Single
import javax.inject.Inject

class GetProvincesUseCase @Inject constructor(
    private val userRepository: UserDataSource,
    schedulersProvider: SchedulersProvider
) : SingleUnitUseCase<MutableList<String>>(schedulersProvider) {

    override fun createUseCaseSingle(): Single<MutableList<String>> {
        return userRepository.getProvinces()
    }
}