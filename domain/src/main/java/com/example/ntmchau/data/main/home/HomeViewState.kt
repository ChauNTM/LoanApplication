package com.example.ntmchau.data.main.home

import com.example.ntmchau.data.base.viewstate.BaseViewState
import com.example.ntmchau.data.entity.Bank
import com.example.ntmchau.data.entity.OfferInfo

data class HomeViewState(val offerInfo: OfferInfo = OfferInfo(0, 0, 0, 0, 0f, Bank("", ""))): BaseViewState

sealed class HomeAction

object GetOfferInfo: HomeAction()
data class UpdateOfferInfo(val offerInfo: OfferInfo) : HomeAction()
object ShowConnectionError : HomeAction()
