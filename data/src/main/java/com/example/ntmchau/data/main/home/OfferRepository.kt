package com.example.ntmchau.data.main.home

import com.example.ntmchau.data.entity.OfferInfo
import com.example.ntmchau.data.retrofit.ApiInterface
import io.reactivex.Single
import javax.inject.Inject

class OfferRepository @Inject constructor(private val apiInterface: ApiInterface):
    OfferDataSource {

    override fun getOffer(): Single<OfferInfo> {
        return apiInterface.getOfferInfo().map { it.toOfferInfo() }
    }
}