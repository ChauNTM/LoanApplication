package com.example.ntmchau.data

import com.example.ntmchau.data.retrofit.ApiInterface
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class ApiCommons {

    companion object {
        const val TEST_BASE_URL = "https://us-central1-loanmockapi-21ba5.cloudfunctions.net/"

        @JvmStatic
        fun retrofit(): Retrofit {
            val builder = OkHttpClient.Builder()

            val logInterceptor = HttpLoggingInterceptor()

            builder.addInterceptor(logInterceptor)


            return Retrofit.Builder()
                .baseUrl(TEST_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson()))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(builder.build())
                .build()
        }

        @JvmStatic
        fun gson(): Gson {
            return GsonBuilder().create()
        }

        @JvmStatic
        fun api(): ApiInterface {
            return retrofit().create(ApiInterface::class.java)
        }
    }
}
