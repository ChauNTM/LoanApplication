package com.example.ntmchau.data.offer

import com.example.ntmchau.data.ApiCommons
import com.example.ntmchau.data.entity.OfferInfo
import com.example.ntmchau.data.main.home.OfferDataSource
import com.example.ntmchau.data.main.home.OfferRepository
import io.reactivex.observers.TestObserver
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.MockitoAnnotations

class OfferRepositoryTest {

    private lateinit var offerRepository: OfferDataSource
    private val testObserver = TestObserver<OfferInfo>()


    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        offerRepository = OfferRepository(ApiCommons.api())
    }

    @Test
    fun `Offer information is properly retrieved from the API`() {
        val result = offerRepository.getOffer().subscribe(testObserver)
        Assert.assertNotNull(result)
        testObserver.awaitTerminalEvent()
        testObserver.assertNoErrors()

        testObserver.assertValueCount(1)
    }
}