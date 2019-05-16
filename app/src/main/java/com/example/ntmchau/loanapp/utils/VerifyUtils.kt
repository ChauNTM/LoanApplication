package com.example.ntmchau.loanapp.utils

import android.content.Context
import com.example.ntmchau.loanapp.R

class VerifyUtils (private val applicationContext: Context) {

    companion object {
        const val LOWEST_MONTHLY_INCOME = 3000000L
    }

    val valid4PrefixPhoneNumbers = mutableListOf(
        "0120", "0121", "0122", "0123", "0124", "0125", "0126", "0127", "0128", "0129",
        "0162", "0163", "0164", "0165", "0166", "0167", "0168", "0169", "0186",
        "0188", "0199"
    )
    val valid3PrefixPhoneNumbers = mutableListOf(
        "086", "088", "089", "090", "091", "092", "093", "094",
        "095", "096", "097", "098", "099"
    )

    fun verifyPhoneNumber(phoneNumber: String): Pair<Boolean, VerifyError?> {
        return if (valid4PrefixPhoneNumbers.contains(phoneNumber.substring(0, 4))) {
            if (phoneNumber.length == 11) {
                Pair(true, null)
            } else {
                Pair(false, PhoneNumberInvalidPrefix(applicationContext.getString(R.string.phone_number_not_enough_characters)))
            }
        } else if (valid3PrefixPhoneNumbers.contains(phoneNumber.substring(0, 3))){
            if (phoneNumber.length == 10) {
                Pair(true, null)
            } else {
                Pair(false, PhoneNumberInvalidPrefix(applicationContext.getString(R.string.phone_number_not_enough_characters)))
            }
        } else {
            Pair(false, PhoneNumberNotEnoughCharacter(applicationContext.getString(R.string.phone_number_invalid_prefix)))

        }
    }

    fun verifyNationalIdNumber(nationalIdNumber: String): Pair<Boolean, VerifyError?> {

        if (nationalIdNumber.length != 9 && nationalIdNumber.length != 12) return Pair(
            false,
            NationalIdNumberNotEnoughCharacter(applicationContext.getString(R.string.national_id_number_not_enough_characters))
        )

        return Pair(true, null)
    }
}