package com.techyourchance.dagger2course.di

import android.view.LayoutInflater
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import com.techyourchance.dagger2course.networking.StackoverflowApi
import com.techyourchance.dagger2course.screens.common.ViewMvcFactory
import dagger.Component
import dagger.Provides

@Component(modules = [ActivityModule::class])
interface ActivityComponent {

    fun activity(): FragmentActivity

    fun layoutInflater(): LayoutInflater

    fun viewMvcFactory(): ViewMvcFactory

    fun fragmentManager(): FragmentManager

    fun stackoverflowApi(): StackoverflowApi
}