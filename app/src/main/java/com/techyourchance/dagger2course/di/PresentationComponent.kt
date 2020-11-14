package com.techyourchance.dagger2course.di

import com.techyourchance.dagger2course.networking.FetchQuestionDetailsUseCase
import com.techyourchance.dagger2course.networking.FetchQuestionListUseCase
import com.techyourchance.dagger2course.screens.common.ViewMvcFactory
import com.techyourchance.dagger2course.screens.common.dialogs.DialogNavigator
import dagger.Component

@Component(modules = [PresentationModule::class])
interface PresentationComponent {

    fun viewMvcFactory(): ViewMvcFactory

    fun dialogNavigator(): DialogNavigator

    fun fetchQuestionsListUseCase(): FetchQuestionListUseCase

    fun fetchQuestionDetailsUseCase(): FetchQuestionDetailsUseCase

}