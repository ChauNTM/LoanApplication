package com.example.ntmchau.loanapp.utils

sealed class VerifyError(val error: String)

class PhoneNumberNotEnoughCharacter(error: String) : VerifyError(error)
class PhoneNumberInvalidPrefix(error: String) : VerifyError(error)
class NationalIdNumberNotEnoughCharacter(error: String) : VerifyError(error)