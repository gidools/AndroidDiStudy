package com.techyourchance.dagger2course.di

import android.app.Application
import com.techyourchance.dagger2course.Constants
import com.techyourchance.dagger2course.networking.StackoverflowApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
class AppModule(private val application: Application) {

    private val retrofit by lazy {
        Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
    }

    val stackoverflowApi by lazy {
        retrofit.create(StackoverflowApi::class.java)
    }

    @AppScope
    @Provides
    fun application() = application

    @AppScope
    @Provides
    fun stackoverflowApi() = stackoverflowApi

}
