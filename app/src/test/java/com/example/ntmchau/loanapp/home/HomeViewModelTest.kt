package com.example.ntmchau.loanapp.home

import androidx.lifecycle.Observer
import com.example.ntmchau.data.entity.Bank
import com.example.ntmchau.data.entity.OfferInfo
import com.example.ntmchau.data.main.home.GetOfferInfoUseCase
import com.example.ntmchau.data.main.home.HomeViewState
import com.example.ntmchau.loanapp.helpers.verifyViewStates
import com.example.ntmchau.loanapp.main.home.HomeViewModel
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class HomeViewModelTest {

    @Mock
    private lateinit var testObserver: Observer<HomeViewState>

    @Mock
    private lateinit var getOfferInfoUseCase: GetOfferInfoUseCase

    private lateinit var viewModel: HomeViewModel

    private val offerInfo = OfferInfo(30000000, 100000000, 6, 18, 19.99f, Bank("Vietcombank", "mock_logo_url"))

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)

        viewModel = HomeViewModel(getOfferInfoUseCase)

        viewModel.viewState().observeForever(testObserver)

        Mockito.`when`(getOfferInfoUseCase.execute()).thenReturn(Single.just(offerInfo))
    }

    @Test
    fun `When ViewModel is initialised proper view states are emitted`() {
        viewModel.init()

        testObserver.verifyViewStates(
            HomeViewState(offerInfo)
        )
    }
}