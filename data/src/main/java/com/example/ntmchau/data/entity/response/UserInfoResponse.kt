package com.example.ntmchau.data.entity.response

import com.example.ntmchau.data.entity.UserInfo
import com.google.gson.annotations.SerializedName

class UserInfoResponse(
    @field:SerializedName("id") var id: Long,
    @field:SerializedName("full_name") var fullName: String,
    @field:SerializedName("phone_number") var phoneNumber: String,
    @field:SerializedName("national_id_number") var nationalIdNumber: String,
    @field:SerializedName("monthly_income") var monthlyIncome: Long,
    @field:SerializedName("province") var province: String
) {
    fun toUserInfo() = UserInfo(id, fullName, phoneNumber, nationalIdNumber, monthlyIncome, province)
}