package com.techyourchance.dagger2course.di

import com.techyourchance.dagger2course.screens.questiondetails.QuestionDetailsActivity
import com.techyourchance.dagger2course.screens.questionslist.QuestionListFragment

class Injector(private val compositionRoot: PresentationCompositionRoot) {

    fun inject(questionListFragment: QuestionListFragment) {
        questionListFragment.dialogNavigator = compositionRoot.dialogNavigator
        questionListFragment.fetchQuestionListUseCase = compositionRoot.fetchQuestionListUseCase
        questionListFragment.viewMvcFactory = compositionRoot.viewMvcFactory
    }

    fun inject(questionDetailsActivity: QuestionDetailsActivity) {
        questionDetailsActivity.dialogNavigator = compositionRoot.dialogNavigator
        questionDetailsActivity.questionDetailsUseCase = compositionRoot.fetchQuestionDetailsUseCase
        questionDetailsActivity.viewMvcFactory = compositionRoot.viewMvcFactory
    }

}