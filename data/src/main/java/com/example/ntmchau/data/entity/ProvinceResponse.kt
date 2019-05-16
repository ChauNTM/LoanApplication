package com.example.ntmchau.data.entity

import com.example.ntmchau.data.entity.response.Bank
import com.google.gson.annotations.SerializedName

class ProvinceResponse(@field:SerializedName("total") var total: Int,
                       @field:SerializedName("provinces_list") var provinces: MutableList<String>
)