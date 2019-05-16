package com.example.ntmchau.loanapp.main.home

import com.example.ntmchau.data.base.viewstate.Reducer
import com.example.ntmchau.data.main.home.*
import com.example.ntmchau.loanapp.base.DispatcherViewModel
import javax.inject.Inject

class HomeViewModel @Inject constructor(private val getOfferInfoUseCase: GetOfferInfoUseCase): DispatcherViewModel<HomeViewState, HomeAction>() {

    override val reducer: Reducer<HomeViewState, HomeAction> = HomeReducer()

    override val defaultState: HomeViewState = HomeViewState()

    override fun init() {
        dispatch(GetOfferInfo)
    }

    override fun dispatchAsync(state: HomeViewState, action: HomeAction) {
        when (action) {
            is GetOfferInfo -> getOfferInfo()
        }
    }

    private fun getOfferInfo() {
        manage(getOfferInfoUseCase.execute().subscribe(
            { offerInfo -> dispatch(UpdateOfferInfo(offerInfo)) },
            { _ -> dispatch(ShowConnectionError) }
        ))
    }

}