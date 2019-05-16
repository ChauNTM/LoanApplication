package com.example.ntmchau.data.main.home

import com.example.ntmchau.data.base.SchedulersProvider
import com.example.ntmchau.data.base.usecase.unit.SingleUnitUseCase
import com.example.ntmchau.data.entity.OfferInfo
import io.reactivex.Single
import javax.inject.Inject

class GetOfferInfoUseCase @Inject constructor(
    private val offerRepository: OfferDataSource,
    schedulersProvider: SchedulersProvider
) : SingleUnitUseCase<OfferInfo>(schedulersProvider) {

    override fun createUseCaseSingle(): Single<OfferInfo> {
        return offerRepository.getOffer()
    }
}