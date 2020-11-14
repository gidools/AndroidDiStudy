package com.techyourchance.dagger2course.screens.common

import androidx.appcompat.app.AppCompatActivity
import com.techyourchance.dagger2course.MyApplication
import com.techyourchance.dagger2course.di.*

abstract class BaseActivity : AppCompatActivity() {

    private val appComponent get() = (application as MyApplication).appComponent

    val activityComponent: ActivityComponent by lazy {
        DaggerActivityComponent.builder()
                .appComponent(appComponent)
                .activityModule(ActivityModule(this))
                .build()
    }

    private val presentationComponent: PresentationComponent by lazy {
        DaggerPresentationComponent.builder()
                .activityComponent(activityComponent)
                .presentationModule(PresentationModule())
                .build()
    }

    protected val injector get() = presentationComponent

}