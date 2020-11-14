package com.techyourchance.dagger2course

import android.app.Application
import com.techyourchance.dagger2course.di.AppComponent
import com.techyourchance.dagger2course.di.AppModule
import com.techyourchance.dagger2course.di.DaggerAppComponent

class MyApplication : Application() {

    val appComponent: AppComponent by lazy {
        DaggerAppComponent.builder()
                .appModule(AppModule(this))
                .build()
    }
}