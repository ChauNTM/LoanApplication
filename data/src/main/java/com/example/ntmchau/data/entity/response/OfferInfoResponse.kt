package com.example.ntmchau.data.entity.response

import com.example.ntmchau.data.entity.OfferInfo
import com.google.gson.annotations.SerializedName

class OfferInfoResponse(
    @field:SerializedName("min_amount") var minAmount: Long,
    @field:SerializedName("max_amount") var maxAmount: Long,
    @field:SerializedName("min_tenor") var minTenor: Int,
    @field:SerializedName("max_tenor") var maxTenor: Int,
    @field:SerializedName("interest_rate") var interestRate: Float,
    @field:SerializedName("bank") var bank: Bank
) {
    val amount = "$minAmount/$maxAmount"
    val tenor = "$minTenor/$maxTenor"

    fun toOfferInfo() = OfferInfo(
        this.minAmount,
        this.maxAmount,
        this.minTenor,
        this.maxTenor,
        this.interestRate,
        com.example.ntmchau.data.entity.Bank(this.bank.name, this.bank.logo)
    )

}

class Bank(
    @field:SerializedName("name") var name: String,
    @field:SerializedName("logo") var logo: String
)