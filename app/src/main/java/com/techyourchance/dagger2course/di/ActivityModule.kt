package com.techyourchance.dagger2course.di

import android.view.LayoutInflater
import androidx.fragment.app.FragmentActivity
import com.techyourchance.dagger2course.screens.common.ViewMvcFactory
import dagger.Module
import dagger.Provides

@Module
class ActivityModule(private val activity: FragmentActivity,
                     private val appComponent: AppComponent) {

    @Provides
    fun activity() = activity

    @Provides
    fun application() = appComponent.application()

    @Provides
    fun layoutInflater() = activity.layoutInflater

    @Provides
    fun fragmentManager() = activity.supportFragmentManager

    @Provides
    fun stackoverflowApi() = appComponent.stackoverflowApi()

}