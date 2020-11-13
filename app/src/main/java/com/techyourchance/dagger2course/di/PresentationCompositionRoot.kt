package com.techyourchance.dagger2course.di

import com.techyourchance.dagger2course.networking.FetchQuestionDetailsUseCase
import com.techyourchance.dagger2course.networking.FetchQuestionListUseCase
import com.techyourchance.dagger2course.screens.common.ViewMvcFactory
import com.techyourchance.dagger2course.screens.common.dialogs.DialogNavigator

class PresentationCompositionRoot(private val compositionRoot: ActivityCompositionRoot) {

    private val activity get() = compositionRoot.activity

    val layoutInflater get() = compositionRoot.layoutInflater

    val viewMvcFactory get() = ViewMvcFactory(compositionRoot.layoutInflater)

    val fragmentManager get() = compositionRoot.fragmentManager

    val stackoverflowApi get() = compositionRoot.stackoverflowApi

    val fetchQuestionListUseCase get() = FetchQuestionListUseCase(stackoverflowApi)

    val fetchQuestionDetailsUseCase get() = FetchQuestionDetailsUseCase(stackoverflowApi)

    val dialogNavigator get() = DialogNavigator(fragmentManager)
}