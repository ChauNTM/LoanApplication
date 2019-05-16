package com.example.ntmchau.loanapp.di

import android.app.Application
import android.content.Context
import com.example.ntmchau.data.BASE_URL
import com.example.ntmchau.data.base.SchedulersProvider
import com.example.ntmchau.data.main.home.OfferDataSource
import com.example.ntmchau.data.main.home.OfferRepository
import com.example.ntmchau.data.main.register.UserDataSource
import com.example.ntmchau.data.main.register.UserRepository
import com.example.ntmchau.data.page.AndroidSchedulersProvider
import com.example.ntmchau.data.retrofit.ApiInterface
import com.example.ntmchau.loanapp.BuildConfig
import com.example.ntmchau.loanapp.LoanApp
import com.example.ntmchau.loanapp.utils.VerifyUtils
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Binds
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module(includes = [ViewModelModule::class])
abstract class AppModule {
    @Binds
    abstract fun bindApplication(app: LoanApp): Application

    @Binds
    abstract fun bindContext(app: LoanApp): Context

    @Binds
    abstract fun bindSchedulersProvider(androidSchedulersProvider: AndroidSchedulersProvider): SchedulersProvider

    @Binds
    abstract fun bindOfferRepository(offerRepository: OfferRepository): OfferDataSource

    @Binds
    abstract fun bindUserRepository(userRepository: UserRepository): UserDataSource

    @Module
    companion object {

        @JvmStatic
        @Provides
        @Named("retrofit")
        @Singleton
        fun retrofitHttpClient(): OkHttpClient {
            val builder = OkHttpClient.Builder()
            builder.retryOnConnectionFailure(true)

            if (BuildConfig.DEBUG) {
                val logInterceptor = HttpLoggingInterceptor()
                logInterceptor.level = HttpLoggingInterceptor.Level.BODY
                builder.addNetworkInterceptor(logInterceptor)
                builder.addInterceptor(logInterceptor)
            }

            return builder.build()
        }

        @JvmStatic
        @Provides
        @Named("glide")
        @Singleton
        fun glideHttpClient(): OkHttpClient {
            val builder = OkHttpClient.Builder()
            builder.retryOnConnectionFailure(true)

            if (BuildConfig.DEBUG) {
                val logInterceptor = HttpLoggingInterceptor()
                logInterceptor.level = HttpLoggingInterceptor.Level.BODY
                builder.addNetworkInterceptor(logInterceptor)
                builder.addInterceptor(logInterceptor)
            }
            return builder.build()
        }

        @JvmStatic
        @Provides
        @Singleton
        fun gson(): Gson {
            return GsonBuilder().create()
        }

        @JvmStatic
        @Provides
        @Singleton
        fun retrofit(@Named("retrofit") okHttpClient: OkHttpClient, gson: Gson): Retrofit {
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient)
                .build()
        }

        @JvmStatic
        @Provides
        @Singleton
        fun apiInterface(retrofit: Retrofit): ApiInterface {
            return retrofit.create(ApiInterface::class.java)
        }

        @JvmStatic
        @Provides
        @Singleton
        fun verifyUtils(app: LoanApp): VerifyUtils {
            return VerifyUtils(app)
        }
    }
}