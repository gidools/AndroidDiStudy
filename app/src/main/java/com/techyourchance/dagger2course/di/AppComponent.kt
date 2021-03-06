package com.techyourchance.dagger2course.di

import android.app.Application
import com.techyourchance.dagger2course.networking.StackoverflowApi
import dagger.Component

@AppScope
@Component(modules = [AppModule::class])
interface AppComponent {

    fun application(): Application

    fun stackoverflowApi(): StackoverflowApi
}