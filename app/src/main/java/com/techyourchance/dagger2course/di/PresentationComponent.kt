package com.techyourchance.dagger2course.di

import com.techyourchance.dagger2course.screens.questiondetails.QuestionDetailsActivity
import com.techyourchance.dagger2course.screens.questionslist.QuestionListFragment
import dagger.Component

@Component(dependencies = [ActivityComponent::class], modules = [PresentationModule::class])
interface PresentationComponent {

    fun inject(questionDetailsActivity: QuestionDetailsActivity)

    fun inject(questionDetailsActivity: QuestionListFragment)

}