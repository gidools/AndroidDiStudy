package com.techyourchance.dagger2course.di

import com.techyourchance.dagger2course.networking.FetchQuestionDetailsUseCase
import com.techyourchance.dagger2course.networking.FetchQuestionListUseCase
import com.techyourchance.dagger2course.screens.common.ViewMvcFactory
import com.techyourchance.dagger2course.screens.common.dialogs.DialogNavigator
import com.techyourchance.dagger2course.screens.questiondetails.QuestionDetailsActivity
import com.techyourchance.dagger2course.screens.questionslist.QuestionListFragment
import dagger.Component

@Component(modules = [PresentationModule::class])
interface PresentationComponent {
//
//    fun viewMvcFactory(): ViewMvcFactory
//
//    fun dialogNavigator(): DialogNavigator
//
//    fun fetchQuestionsListUseCase(): FetchQuestionListUseCase
//
//    fun fetchQuestionDetailsUseCase(): FetchQuestionDetailsUseCase

    fun inject(questionDetailsActivity: QuestionDetailsActivity)

    fun inject(questionDetailsActivity: QuestionListFragment)

}