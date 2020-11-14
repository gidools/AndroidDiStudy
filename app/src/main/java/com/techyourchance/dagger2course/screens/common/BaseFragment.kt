package com.techyourchance.dagger2course.screens.common

import androidx.fragment.app.Fragment
import com.techyourchance.dagger2course.di.ActivityComponent
import com.techyourchance.dagger2course.di.DaggerPresentationComponent
import com.techyourchance.dagger2course.di.PresentationModule

abstract class BaseFragment: Fragment() {

    private val compositionRoot by lazy {
        DaggerPresentationComponent.builder()
                .activityComponent((requireActivity() as BaseActivity).activityComponent)
                .presentationModule(PresentationModule())
                .build()
    }

    protected val injector get() = compositionRoot
}