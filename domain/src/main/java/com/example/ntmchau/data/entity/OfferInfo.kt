package com.example.ntmchau.data.entity


data class OfferInfo(
    val minAmount: Long,
    val maxAmount: Long,
    val minTenor: Int,
    val maxTenor: Int,
    val interestRate: Float,
    val bank: Bank
) {

    val amount = "$minAmount/$maxAmount"
    val tenor = "$minTenor/$maxTenor"
    val interestRateValue = interestRate.toString()

}

class Bank(val name: String, val logo: String)