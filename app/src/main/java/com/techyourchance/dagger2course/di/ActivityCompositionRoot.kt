package com.techyourchance.dagger2course.di

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import com.techyourchance.dagger2course.networking.FetchQuestionDetailsUseCase
import com.techyourchance.dagger2course.networking.FetchQuestionListUseCase
import com.techyourchance.dagger2course.screens.common.ViewMvcFactory
import com.techyourchance.dagger2course.screens.common.dialogs.DialogNavigator

class ActivityCompositionRoot(val activity: AppCompatActivity,
                              private val appCompositionRoot: AppCompositionRoot) {

    val application get() = appCompositionRoot.application

    val layoutInflater get() = activity.layoutInflater

    val viewMvcFactory get() = ViewMvcFactory(layoutInflater)

    val fragmentManager get() = activity.supportFragmentManager

    val stackoverflowApi get() = appCompositionRoot.stackoverflowApi

}