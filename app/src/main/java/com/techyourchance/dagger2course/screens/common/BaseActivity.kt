package com.techyourchance.dagger2course.screens.common

import androidx.appcompat.app.AppCompatActivity
import com.techyourchance.dagger2course.MyApplication
import com.techyourchance.dagger2course.di.ActivityCompositionRoot
import com.techyourchance.dagger2course.di.PresentationCompositionRoot

abstract class BaseActivity : AppCompatActivity() {

    protected val appCompositionRoot get() = (application as MyApplication).appCompositionRoot

    val activityCompositionRoot by lazy {
        ActivityCompositionRoot(this, appCompositionRoot)
    }

    protected val compositionRoot by lazy {
        PresentationCompositionRoot(activityCompositionRoot)
    }

}