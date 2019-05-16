package com.example.ntmchau.data.retrofit

import com.example.ntmchau.data.entity.ProvinceResponse
import com.example.ntmchau.data.entity.response.OfferInfoResponse
import com.example.ntmchau.data.entity.response.UserInfoResponse
import io.reactivex.Single
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiInterface {

    @GET("offer")
    fun getOfferInfo(): Single<OfferInfoResponse>

    @GET("province")
    fun getProvinces(): Single<ProvinceResponse>

    @POST("loan")
    @FormUrlEncoded
    fun sendRequestRegister(
        @Field("full_name") fullName: String,
        @Field("phone_number") phoneNumber: String,
        @Field("national_id_number") nationalIdNumber: String,
        @Field("monthly_income") monthlyIncome: Long,
        @Field("province") province: String
    ): Single<UserInfoResponse>

}