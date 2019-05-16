package com.example.ntmchau.data.main.home

import com.example.ntmchau.data.base.viewstate.Reducer
import com.example.ntmchau.data.entity.OfferInfo

class HomeReducer : Reducer<HomeViewState, HomeAction> {

    override fun reduce(state: HomeViewState, action: HomeAction): HomeViewState {
        return HomeViewState(reduceOfferInfo(state, action))
    }

    private fun reduceOfferInfo(state: HomeViewState, action: HomeAction): OfferInfo {
        return when(action) {
            is UpdateOfferInfo -> action.offerInfo
            else -> state.offerInfo
        }
    }

}