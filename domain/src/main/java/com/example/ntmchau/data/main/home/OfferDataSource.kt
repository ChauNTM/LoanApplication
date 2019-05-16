package com.example.ntmchau.data.main.home

import com.example.ntmchau.data.entity.OfferInfo
import io.reactivex.Single

interface OfferDataSource {
    fun getOffer(): Single<OfferInfo>
}