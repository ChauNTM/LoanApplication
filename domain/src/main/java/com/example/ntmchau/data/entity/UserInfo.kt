package com.example.ntmchau.data.entity

data class UserInfo(
    val id: Long,
    val fullName: String,
    val phoneNumber: String,
    val nationalIdNumber: String,
    val monthlyIncome: Long,
    val province: String
)