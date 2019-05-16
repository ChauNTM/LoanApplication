package com.example.ntmchau.data.offer

import com.example.ntmchau.data.ApiCommons
import com.example.ntmchau.data.entity.Bank
import com.example.ntmchau.data.entity.OfferInfo
import com.example.ntmchau.data.entity.response.OfferInfoResponse
import org.junit.Assert
import org.junit.Test

class OfferRepositoryMappingTest {

    private val expected: OfferInfo = OfferInfo(30000000, 100000000, 6, 18, 19.99f, Bank("Vietcombank", "https://www.vietcombank.com.vn/images/logo30.png"))

    @Test
    fun `User info from province json file are properly mapped to domain object`() {
        val offerResponse = parseJsonFromFile("offer_info.json")
        Assert.assertEquals(expected, offerResponse.toOfferInfo())
    }

    private fun parseJsonFromFile(filename: String): OfferInfoResponse {
        val bufferedReader = javaClass.classLoader.getResourceAsStream(filename).bufferedReader()
        val inputString = bufferedReader.use { it.readText() }
        return ApiCommons.gson().fromJson<OfferInfoResponse>(inputString, OfferInfoResponse::class.java)
    }
}