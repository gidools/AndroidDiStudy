package com.techyourchance.dagger2course.di

import android.view.LayoutInflater
import androidx.fragment.app.FragmentManager
import com.techyourchance.dagger2course.networking.FetchQuestionDetailsUseCase
import com.techyourchance.dagger2course.networking.FetchQuestionListUseCase
import com.techyourchance.dagger2course.networking.StackoverflowApi
import com.techyourchance.dagger2course.screens.common.ViewMvcFactory
import com.techyourchance.dagger2course.screens.common.dialogs.DialogNavigator
import dagger.Module
import dagger.Provides

@Module
class PresentationModule(private val compositionRoot: ActivityCompositionRoot) {

    @Provides
    fun activit() = compositionRoot.activity

    @Provides
    fun layoutInflater() = compositionRoot.layoutInflater

    @Provides
    fun viewMvcFactory(layoutInflater: LayoutInflater) = ViewMvcFactory(layoutInflater)

    @Provides
    fun fragmentManager() = compositionRoot.fragmentManager

    @Provides
    fun stackoverflowApi() = compositionRoot.stackoverflowApi

    @Provides
    fun fetchQuestionListUseCase(stackoverflowApi: StackoverflowApi)
            = FetchQuestionListUseCase(stackoverflowApi)

    @Provides
    fun fetchQuestionDetailsUseCase(stackoverflowApi: StackoverflowApi)
            = FetchQuestionDetailsUseCase(stackoverflowApi)

    @Provides
    fun dialogNavigator(fragmentManager: FragmentManager) = DialogNavigator(fragmentManager)

}