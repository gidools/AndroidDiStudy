package com.techyourchance.dagger2course

import android.app.Application
import com.techyourchance.dagger2course.di.AppCompositionRoot

class MyApplication : Application() {

    lateinit var appCompositionRoot: AppCompositionRoot

    override fun onCreate() {
        super.onCreate()

        appCompositionRoot = AppCompositionRoot(this)
    }
}