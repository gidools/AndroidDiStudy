package com.techyourchance.dagger2course.di

import android.app.Activity
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentActivity
import com.techyourchance.dagger2course.networking.FetchQuestionDetailsUseCase
import com.techyourchance.dagger2course.networking.FetchQuestionListUseCase
import com.techyourchance.dagger2course.screens.common.ViewMvcFactory
import com.techyourchance.dagger2course.screens.common.dialogs.DialogNavigator
import dagger.Module
import dagger.Provides

@Module
class ActivityModule(val activity: FragmentActivity,
                     private val appCompositionRoot: AppCompositionRoot) {

    @Provides
    fun activity() = activity

    @Provides
    fun application() = appCompositionRoot.application

    @Provides
    fun layoutInflater() = activity.layoutInflater

    @Provides
    fun viewMvcFactory(layoutInflater: LayoutInflater) = ViewMvcFactory(layoutInflater)

    @Provides
    fun fragmentManager() = activity.supportFragmentManager

    @Provides
    fun stackoverflowApi() = appCompositionRoot.stackoverflowApi

}